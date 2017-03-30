package ukma.groupproject.shop.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Employee;

@Component
@Scope("prototype")
public class MainController extends Controller {
    @FXML
    private MenuItem departmentsMenuItem;
    @FXML
    private MenuItem itemsMenuItem;
    @FXML
    private MenuItem ordersMenuItem;
    @FXML
    private MenuItem suppliersMenuItem;
    @FXML
    private MenuItem suppliesMenuItem;
    @FXML
    private MenuItem employeesMenuItem;
    @FXML
    private MenuItem purchasesMenuItem;

    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private TabPane mainTabPane;

    @FXML
    private Button loginButton;
    @FXML
    private Label employeeNameLabel;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    private ItemsTabController itemsTabController;
    private OrdersTabController ordersTabController;
    private SuppliersTabController suppliersTabController;
    private DepartmentsTabController departmentsTabController;
    private EmployeesTabController employeesTabController;
    private PurchasesTabController purchasesTabController;

    
    static public ObjectProperty<Employee> employee;
    
    private EventHandler<ActionEvent> loginHandler;
    private EventHandler<ActionEvent> logoutHandler;

    @Override
    public void initialize() {
        employee = new SimpleObjectProperty<>();
        loginHandler = event -> {
            LoginController loginController = (LoginController) fxmlLoader.load("views/Login.fxml");
            Scene loginScene = new Scene((Parent) loginController.getView());
            loginScene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(loginScene);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.initOwner(getView().getScene().getWindow());
            loginStage.showAndWait();

            if (loginController.getEmployee() != null) {
                employee.set(loginController.getEmployee());
                employeeNameLabel.setText(loginController.getEmployee().getName());
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

        itemsMenuItem.setOnAction(event -> addTab("Items", itemsTabController, "views/ItemsTab.fxml"));
        ordersMenuItem.setOnAction(event -> {
        	if (employee.get() != null) 
        		addTab("Orders", ordersTabController, "views/OrdersTab.fxml");
        	else
        	{
        		Alert alert = new Alert(AlertType.ERROR, "Please log in to see your orders.", ButtonType.OK);
       		 	alert.initModality(Modality.APPLICATION_MODAL);
       		 	alert.showAndWait();
        	}
        });
        suppliersMenuItem.setOnAction(event -> addTab("Suppliers", suppliersTabController, "views/SuppliersTab.fxml"));
        departmentsMenuItem.setOnAction(event -> addTab("Departments", departmentsTabController, "views/DepartmentsTab.fxml"));
        employeesMenuItem.setOnAction(event -> addTab("Employees", employeesTabController, "views/EmployeesTab.fxml"));
        purchasesMenuItem.setOnAction(event -> addTab("Purchases", purchasesTabController, "views/PurchasesTab.fxml"));

        exitMenuItem.setOnAction(event -> Platform.exit());
    }

    private void addTab(String name, Controller controller, String viewPath) {
        if (controller == null)
            controller = fxmlLoader.load(viewPath);

        for (Tab tab : mainTabPane.getTabs()) {
            if (tab.getText().equals(name)) {
                mainTabPane.getSelectionModel().select(tab);
                return;
            }
        }

        Tab tab = new Tab(name);
        tab.setContent(controller.getView());
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }
}
