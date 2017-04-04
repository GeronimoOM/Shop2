package ukma.groupproject.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.OrderFactory;
import ukma.groupproject.shop.service.OrderService;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class CreateOrderController extends Controller {

	@FXML TextField itemTextField;
	@FXML TextField amountTextField;
	@FXML ListView<Supplier> suppliersList;
	@FXML Button createButton;

	@Autowired private SupplierService supplierService;
	@Autowired private OrderService orderService;
	@Autowired private OrderFactory orderFactory;
	@Autowired private MainController mainController;

    private Service<List<Supplier>> getSuppliersService;
	private ObservableList<Supplier> suppliers;
	private Item item;
    private Order order;

	@Override
	public void initialize() {
		suppliers = FXCollections.observableArrayList();
		
		getSuppliersService = new Service<List<Supplier>>() {
            @Override
            protected Task<List<Supplier>> createTask() {
                Task<List<Supplier>> getItemsTask = new Task<List<Supplier>>() {
                    @Override
                    protected List<Supplier> call() throws Exception {
                        return supplierService.getSupplying(item);
                    }
                };
                return getItemsTask;
            }
        };
        getSuppliersService.setOnSucceeded(event -> suppliers.setAll(getSuppliersService.getValue()));

		suppliersList.setCellFactory(param -> new ListCell<Supplier>() {
			@Override
			protected void updateItem(Supplier supplier, boolean empty) {
				super.updateItem(supplier, empty);
				this.setText(supplier == null ? "": supplier.getName());
			}
		});
		suppliersList.setItems(suppliers);

		createButton.setOnAction(e -> {
            Integer amount = null;
            try {
                amount = Integer.parseInt(amountTextField.getText());
            } catch (NumberFormatException ex) {
                showErrorAlert("Not a valid amount");
                return;
            }
			if (suppliersList.getSelectionModel().getSelectedItem() == null) {
                showErrorAlert("No supplier selected");
                return;
            }
            try {
                Order order = orderFactory.create(
                        mainController.getEmployee(),
                        suppliersList.getSelectionModel().getSelectedItem(),
                        item,
                        amount);
                orderService.persist(order);
                this.order = order;
                ((Stage) view.getScene().getWindow()).close();
            } catch (ShopBusinessException ex) {
                showErrorAlert(ex.getMessage());
            }
		});
	}

	public void setItem(Item item) {
		this.item = item;
		itemTextField.setText(item.getName());
        getSuppliersService.start();
	}

	public Order getOrder() {
        return order;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
