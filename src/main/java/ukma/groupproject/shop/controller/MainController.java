package ukma.groupproject.shop.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class MainController extends Controller {

    @FXML private MenuItem departmentsMenuItem;
    @FXML private MenuItem itemsMenuItem;
    @FXML private MenuItem ordersMenuItem;
    @FXML private MenuItem suppliersMenuItem;
    @FXML private MenuItem suppliesMenuItem;
    @FXML private MenuItem employeesMenuItem;
    @FXML private MenuItem purchasesMenuItem;
    
    @FXML private MenuItem summaryMenuItem;
    @FXML private MenuItem employeeActivityMenuItem;
    @FXML private MenuItem totalWeekMenuTab;
    @FXML private MenuItem totalMonthMenuTab;
    
    @FXML private MenuItem exitMenuItem;

    @FXML private TabPane mainTabPane;

    @FXML private Button loginButton;
    @FXML private Label employeeNameLabel;

    @Autowired private SpringFxmlLoader fxmlLoader;

    static public ObjectProperty<Employee> employee;
    private EventHandler<ActionEvent> loginHandler;
    private EventHandler<ActionEvent> logoutHandler;

    private Map<Controller, Tab> controllerTabs;

    private ItemsTabController itemsTabController = null;
    private OrdersTabController ordersTabController = null;
    private SuppliersTabController suppliersTabController = null;
    private DepartmentsTabController departmentsTabController = null;
    private EmployeesTabController employeesTabController = null;
    private PurchasesTabController purchasesTabController = null;
    private SuppliesTabController suppliesTabController = null;
    private SummaryStatsController summaryStatsController = null;

    @Override
    public void initialize() {
        employee = new SimpleObjectProperty<>();
        loginHandler = event -> {
            LoginController loginController = (LoginController) fxmlLoader.load("views/Login.fxml");
            createModal("Login", loginController).showAndWait();

            if (loginController.getEmployee() != null) {
                employee.set(loginController.getEmployee());
            }
        };
        logoutHandler = event -> employee.set(null);

        employee.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                employeeNameLabel.setText(newValue.getName());
                loginButton.setText("Log Out");
                loginButton.setOnAction(logoutHandler);
            } else {
                employeeNameLabel.setText("");
                loginButton.setText("Log In");
                loginButton.setOnAction(loginHandler);
            }
        });
        loginButton.setOnAction(loginHandler);


        controllerTabs = new HashMap<>();
        itemsMenuItem.setOnAction(event -> addTab("Items", getItemsTabController()));
        ordersMenuItem.setOnAction(event -> {
        	if (employee.get() != null) 
        		addTab("Orders", getOrdersTabController());
        	else {
        		Alert alert = new Alert(AlertType.ERROR, "Please log in to see your orders.", ButtonType.OK);
       		 	alert.initModality(Modality.APPLICATION_MODAL);
       		 	alert.showAndWait();
        	}
        });
        suppliersMenuItem.setOnAction(event -> addTab("Suppliers",getSuppliersTabController()));
        departmentsMenuItem.setOnAction(event -> addTab("Departments", getDepartmentsTabController()));
        employeesMenuItem.setOnAction(event -> addTab("Employees", getEmployeesTabController()));
        purchasesMenuItem.setOnAction(event -> addTab("Purchases", getPurchasesTabController()));
        suppliesMenuItem.setOnAction(event -> addTab("Supplies", getSuppliesTabController()));

        summaryMenuItem.setOnAction(event -> addTab("Day Summary", getSummaryController()));

        exitMenuItem.setOnAction(event -> Platform.exit());
    }

    private void addTab(String name, Controller controller) {
        Tab tab = controllerTabs.get(controller);
        if(tab == null) {
            tab = new Tab(name, controller.getView());
            controllerTabs.put(controller, tab);
        }
        if (!mainTabPane.getTabs().contains(tab))
            mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    private ItemsTabController getItemsTabController() {
        if(itemsTabController == null) {
            itemsTabController = (ItemsTabController) fxmlLoader.load("views/ItemsTab.fxml");
        }
        return itemsTabController;
    }

    private OrdersTabController getOrdersTabController() {
        if(ordersTabController == null) {
            ordersTabController = (OrdersTabController) fxmlLoader.load("views/OrdersTab.fxml");
        }
        return ordersTabController;
    }

    private SuppliersTabController getSuppliersTabController() {
        if(suppliersTabController == null) {
            suppliersTabController = (SuppliersTabController) fxmlLoader.load("views/SuppliersTab.fxml");
        }
        return suppliersTabController;
    }

    private DepartmentsTabController getDepartmentsTabController() {
        if(departmentsTabController == null) {
            departmentsTabController = (DepartmentsTabController) fxmlLoader.load("views/DepartmentsTab.fxml");
        }
        return departmentsTabController;
    }

    private EmployeesTabController getEmployeesTabController() {
        if(employeesTabController == null) {
            employeesTabController = (EmployeesTabController) fxmlLoader.load("views/EmployeesTab.fxml");
        }
        return employeesTabController;
    }

    private PurchasesTabController getPurchasesTabController() {
        if(purchasesTabController == null) {
            purchasesTabController = (PurchasesTabController) fxmlLoader.load("views/PurchasesTab.fxml");
        }
        return purchasesTabController;
    }

    private SuppliesTabController getSuppliesTabController() {
        if(suppliesTabController == null) {
            suppliesTabController = (SuppliesTabController) fxmlLoader.load("views/SuppliesTab.fxml");
        }
        return suppliesTabController;
    }

    private SummaryStatsController getSummaryController() {
        if(summaryStatsController == null)
        	summaryStatsController = (SummaryStatsController) fxmlLoader.load("views/SummaryStats.fxml");
        return summaryStatsController;
    }
}
