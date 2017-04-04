package ukma.groupproject.shop.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.concurrent.Task;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.OrderService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class OrdersTabController extends Controller {

    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Date> dateColumn;
    @FXML private TableColumn<Order, Item> itemColumn;
    @FXML private TableColumn<Order, Integer> amountColumn;
    @FXML private TableColumn<Order, Supplier> supplierColumn;

    @FXML private Button removeButton;

    @Autowired private OrderService orderService;
    @Autowired private SpringFxmlLoader fxmlLoader;
    @Autowired private MainController mainController;

    private ObservableList<Order> orders;
    private Service<List<Order>> getOrdersService;

    private DateFormat dateFormat;

    @Override
    public void initialize() {
        dateColumn.prefWidthProperty().bind(ordersTable.widthProperty().multiply(0.18));
        itemColumn.prefWidthProperty().bind(ordersTable.widthProperty().multiply(0.3));
        amountColumn.prefWidthProperty().bind(ordersTable.widthProperty().multiply(0.2));
        supplierColumn.prefWidthProperty().bind(ordersTable.widthProperty().multiply(0.3));

        dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);

        removeButton.setOnAction(e -> {
            Order order = ordersTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this order ?", ButtonType.YES, ButtonType.NO);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    orderService.delete(order);
                    orders.remove(order);
                } catch (ShopBusinessException ex) {
                    Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    error.initModality(Modality.APPLICATION_MODAL);
                    error.showAndWait();
                }
            }
        });

        orders = FXCollections.observableArrayList();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date") );
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        dateColumn.setCellFactory(param -> new TableCell<Order, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty ? "" : dateFormat.format(date));
            }
        });

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        itemColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getItem()));
        itemColumn.setCellFactory(param -> new TableCell<Order, Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
        supplierColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getSupplier()));
        supplierColumn.setCellFactory(new Callback<TableColumn<Order, Supplier>, TableCell<Order, Supplier>>() {
            @Override
            public TableCell<Order, Supplier> call(TableColumn<Order, Supplier> param) {
                return new TableCell<Order, Supplier>() {
                    @Override
                    protected void updateItem(Supplier supplier, boolean empty) {
                        super.updateItem(supplier, empty);
                        setText(empty ? "" : supplier.getName());
                    }
                };
            }
        });

        getOrdersService = new Service<List<Order>>() {
            @Override
            protected Task<List<Order>> createTask() {
                Task<List<Order>> getOrdersTask = new Task<List<Order>>() {
                    @Override
                    protected List<Order> call() throws Exception {
                        return orderService.getOrdersBy(mainController.getEmployee());
                    }
                };
                return getOrdersTask;
            }
        };
        getOrdersService.start();
        getOrdersService.setOnSucceeded(event -> orders.setAll(getOrdersService.getValue()));
        ordersTable.setItems(orders);
    }

    public ObservableList<Order> getOrders() {
        return orders;
    }
}
