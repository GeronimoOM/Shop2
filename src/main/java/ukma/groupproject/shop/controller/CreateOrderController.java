package ukma.groupproject.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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

@Component
@Scope("prototype")
public class CreateOrderController extends Controller {

	@FXML ListView<Supplier> suppliersList;
	
	@FXML Label itemLabel;

	@FXML Button createButton;

	@FXML TextField amountTextField;

	@Autowired private SupplierService supplierService;
	@Autowired private OrderService orderService;
	@Autowired private OrderFactory orderFactory;


    private Service<List<Supplier>> getSuppliersService;

	private ObservableList<Supplier> suppliers;
	
	public ObjectProperty<Item> item;

	@Override
	public void initialize()
	{
		suppliers = FXCollections.observableList(supplierService.getAll());
		item = new SimpleObjectProperty<>();
		item.addListener(new ChangeListener<Item>()
		{
			@Override
			public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
				itemLabel.setText("Item: " + newValue.getName());
			}
		});
		
		getSuppliersService = new Service<List<Supplier>>() {
            @Override
            protected Task<List<Supplier>> createTask() {
                Task<List<Supplier>> getItemsTask = new Task<List<Supplier>>() {
                    @Override
                    protected List<Supplier> call() throws Exception {
        				// TODO: show only suppliers that have this item
                        return supplierService.getAll();
                    }
                };
                return getItemsTask;
            }
        };
        getSuppliersService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	suppliers.setAll(getSuppliersService.getValue());
            }
        });
		
		suppliersList.setCellFactory(param -> new ListCell<Supplier>() {
			@Override
			protected void updateItem(Supplier supplier, boolean empty) {
				super.updateItem(supplier, empty);
				this.setText(supplier == null ? null : supplier.getName());
			}
		});
		
		suppliersList.setItems(suppliers);

		createButton.setOnAction(e -> {
       	 	createButton.getParent().getParent().setDisable(true);
			if (suppliersList.getSelectionModel().getSelectedItem() == null || amountTextField.getText() == "")
			{
				Alert alert = new Alert(AlertType.ERROR, "Please choose a supplier\nand specify an amount of item to order.", ButtonType.OK);
       		 	alert.initModality(Modality.APPLICATION_MODAL);
       		 	alert.showAndWait();
			}
			else
			{
				Order o = orderFactory.create(
						MainController.employee.get(), 
						suppliersList.getSelectionModel().getSelectedItem(), 
						item.get(), 
						Integer.valueOf(amountTextField.getText()));
				orderService.persist(o);
				 
				((Stage) createButton.getScene().getWindow()).close();				
			}
       	 	createButton.getParent().getParent().setDisable(false);
		});

        // only numbers in amount field
        amountTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) amountTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
	}
}
