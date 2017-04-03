package ukma.groupproject.shop.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.model.Supply;
import ukma.groupproject.shop.service.OrderService;

@Component
@Scope("prototype")
public class OrdersTabController extends Controller {

    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Date> dateColumn;
    @FXML private TableColumn<Order, Item> itemColumn;
    @FXML private TableColumn<Order, Integer> amountColumn;
    @FXML private TableColumn<Order, Supplier> supplierColumn;
    
    @FXML private Button createButton;
    @FXML private Button removeButton;

    @Autowired private OrderService orderService;

    private ObservableList<Order> orders;

    private Service<List<Order>> getOrdersService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    private CreateOrderController createOrderController;
    private Scene createOrderScene;

    @Override
    public void initialize() 
    {        
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Order o = ordersTable.getSelectionModel().getSelectedItem();
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this order?", ButtonType.YES, ButtonType.NO);
            	alert.initModality(Modality.APPLICATION_MODAL);
            	alert.showAndWait();
            	if (alert.getResult() == ButtonType.YES)
            	{
            		orderService.delete(o);
            		refreshOrderService();
            	}
            }
        });

    	orders = FXCollections.observableList(orderService.getOrdersBy(MainController.employee.get()));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date") );
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        dateColumn.setCellFactory(param -> new TableCell<Order, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if(!empty) {
                    setText(dateFormat.format(date));
                }
            }
        });
        
        amountColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("amount"));
        itemColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getItem()));
        itemColumn.setCellFactory(new Callback<TableColumn<Order, Item>, TableCell<Order, Item>>() {
            @Override
            public TableCell<Order, Item> call(TableColumn<Order, Item> param) {
                return new TableCell<Order, Item>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty)
                            setText(item.getName());
                    }
                };
            }
        });
        supplierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, Supplier>, ObservableValue<Supplier>>() {
            @Override
            public ObservableValue<Supplier> call(TableColumn.CellDataFeatures<Order, Supplier> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getSupplier());
            }
        });
        supplierColumn.setCellFactory(new Callback<TableColumn<Order, Supplier>, TableCell<Order, Supplier>>() {
            @Override
            public TableCell<Order, Supplier> call(TableColumn<Order, Supplier> param) {
                return new TableCell<Order, Supplier>() {
                    @Override
                    protected void updateItem(Supplier supplier, boolean empty) {
                        super.updateItem(supplier, empty);
                        if(!empty)
                            setText(supplier.getName());
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
                        return orderService.getOrdersBy(MainController.employee.get());
                    }
                };
                return getOrdersTask;
            }
        };

        refreshOrderService();
        
        getOrdersService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                orders.setAll(getOrdersService.getValue());
            }
        });

        ordersTable.setItems(orders);
    }
    
    void refreshOrderService()
    {
        getOrdersService.reset();
        getOrdersService.start();
    }    
}
