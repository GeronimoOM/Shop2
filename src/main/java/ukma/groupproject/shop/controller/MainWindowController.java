package ukma.groupproject.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ukma.groupproject.shop.context.SpringFxmlLoader;

@Component
public class MainWindowController extends Controller 
{
    @FXML private MenuItem departmentsMenuItem;
    @FXML private MenuItem itemsMenuItem;
    @FXML private MenuItem ordersMenuItem;
    @FXML private MenuItem suppliersMenuItem;
    @FXML private MenuItem suppliesMenuItem;
    @FXML private MenuItem employeesMenuItem;
    @FXML private MenuItem purchasesMenuItem;

    @FXML private MenuItem exitMenuItem;
    
    @FXML private TabPane mainTabPane;

    @Autowired
    private SpringFxmlLoader fxmlLoader;
    
    private ItemsTabController itemsTabController;
    private SuppliersTabController suppliersTabController;
    private DepartmentsTabController departmentsTabController;
    private EmployeesTabController employeesTabController;

    @Override
    public void initialize() 
    {
        itemsTabController = (ItemsTabController) fxmlLoader.load("/views/ItemsTab.fxml");
        suppliersTabController = (SuppliersTabController) fxmlLoader.load("/views/SuppliersTab.fxml");
        departmentsTabController = (DepartmentsTabController) fxmlLoader.load("/views/DepartmentsTab.fxml");
        employeesTabController = (EmployeesTabController) fxmlLoader.load("/views/EmployeesTab.fxml");
    	
    	itemsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { addTab("Items", itemsTabController.getView()); }
        });
    	
    	suppliersMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            { 
            	addTab("Suppliers", suppliersTabController.getView()); 
            	suppliersTabController.refreshSupplierService();
            }
        });
    	
    	departmentsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            { 
            	addTab("Departments", departmentsTabController.getView()); 
            	departmentsTabController.refreshDepartmentService();
            }
        });
    	
    	employeesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            { 
            	addTab("Employees", employeesTabController.getView()); 
            	employeesTabController.refreshEmployeeService();
            }
        });
    	
    	exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            { 
            	Platform.exit();
            }
        });
    }
    
    private void addTab(String name, Node content)
    {
    	for (Tab tab : mainTabPane.getTabs())
    		if (tab.getContent().equals(content))
    		{
    	    	mainTabPane.getSelectionModel().select(tab);
    	    	return;
    		}
    	
    	Tab tab = new Tab(name);
    	tab.setContent(content);
    	mainTabPane.getTabs().add(tab);
    	mainTabPane.getSelectionModel().select(tab);
    }
}
