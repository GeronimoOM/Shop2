package ukma.groupproject.shop.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ObjectProperty<Employee> employee;
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
    private EmployeeStatsController employeeStatsController = null;

    @Override
    public void initialize() {
        employee = new SimpleObjectProperty<>();
        loginHandler = event -> login();
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
        itemsMenuItem.setOnAction(event -> openTab("Items", getItemsTabController()));
        ordersMenuItem.setOnAction(event -> {
            if(employee.get() == null) {
                login();
            }
        	if (employee.get() != null) {
                openTab("Orders", getOrdersTabController());
            }
        });
        suppliersMenuItem.setOnAction(event -> openTab("Suppliers", getSuppliersTabController()));
        departmentsMenuItem.setOnAction(event -> openTab("Departments", getDepartmentsTabController()));
        employeesMenuItem.setOnAction(event -> openTab("Employees", getEmployeesTabController()));
        purchasesMenuItem.setOnAction(event -> openTab("Purchases", getPurchasesTabController()));
        suppliesMenuItem.setOnAction(event -> openTab("Supplies", getSuppliesTabController()));

        summaryMenuItem.setOnAction(event -> openTab("Day Summary", getSummaryController()));
        employeeActivityMenuItem.setOnAction(event -> openTab("Employee Activity", getEmployeeActivityController()));

        exitMenuItem.setOnAction(event -> Platform.exit());

        //openTab("Items", getItemsTabController());
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }

    public ObjectProperty<Employee> employeeProperty() {
        return employee;
    }

    private void login() {
        LoginController loginController = (LoginController) fxmlLoader.load("Login");
        createModal("Login", loginController).showAndWait();

        if (loginController.getEmployee() != null) {
            employee.set(loginController.getEmployee());
        }
    }

    private void openTab(String name, Controller controller) {
        Tab tab = controllerTabs.get(controller);
        if(tab == null) {
            tab = new Tab(name, controller.getView());
            controllerTabs.put(controller, tab);
            tab.setOnClosed(event -> {
                controllerTabs.remove(controller);
            });
        }
        if (!mainTabPane.getTabs().contains(tab))
            mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    public ItemsTabController getItemsTabController() {
        if(itemsTabController == null) {
            itemsTabController = (ItemsTabController) fxmlLoader.load("ItemsTab");
        }
        return itemsTabController;
    }

    public OrdersTabController getOrdersTabController() {
        if(ordersTabController == null) {
            ordersTabController = (OrdersTabController) fxmlLoader.load("OrdersTab");
        }
        return ordersTabController;
    }

    public SuppliersTabController getSuppliersTabController() {
        if(suppliersTabController == null) {
            suppliersTabController = (SuppliersTabController) fxmlLoader.load("SuppliersTab");
        }
        return suppliersTabController;
    }

    public DepartmentsTabController getDepartmentsTabController() {
        if(departmentsTabController == null) {
            departmentsTabController = (DepartmentsTabController) fxmlLoader.load("DepartmentsTab");
        }
        return departmentsTabController;
    }

    public EmployeesTabController getEmployeesTabController() {
        if(employeesTabController == null) {
            employeesTabController = (EmployeesTabController) fxmlLoader.load("EmployeesTab");
        }
        return employeesTabController;
    }

    public PurchasesTabController getPurchasesTabController() {
        if(purchasesTabController == null) {
            purchasesTabController = (PurchasesTabController) fxmlLoader.load("PurchasesTab");
        }
        return purchasesTabController;
    }

    public SuppliesTabController getSuppliesTabController() {
        if(suppliesTabController == null) {
            suppliesTabController = (SuppliesTabController) fxmlLoader.load("SuppliesTab");
        }
        return suppliesTabController;
    }

    private SummaryStatsController getSummaryController() {
        if(summaryStatsController == null)
        	summaryStatsController = (SummaryStatsController) fxmlLoader.load("SummaryStats");
        return summaryStatsController;
    }

    private EmployeeStatsController getEmployeeActivityController() {
        if(employeeStatsController == null)
        	employeeStatsController = (EmployeeStatsController) fxmlLoader.load("EmployeeStats");
        return employeeStatsController;
    }
}
