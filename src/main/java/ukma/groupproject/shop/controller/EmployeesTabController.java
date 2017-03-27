package ukma.groupproject.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.service.EmployeeService;

@Component
public class EmployeesTabController extends Controller {

    @FXML private TableView<Employee> employeesTable;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, Integer> salaryColumn;
    @FXML private TableColumn<Employee, Department> departmentColumn;
    
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private EmployeeService employeeService;

    private ObservableList<Employee> employees;

    private Service<List<Employee>> getEmployeesService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    private CreateEmployeeController createEmployeeController;
    private Scene createEmployeeScene;

    @Override
    public void initialize() 
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	getView().setDisable(true);
            	createEmployeeController = (CreateEmployeeController) fxmlLoader.load("/views/CreateEmployee.fxml");
            	createEmployeeScene = new Scene((Parent) createEmployeeController.getView());
            	createEmployeeScene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);

            	Stage createDepStage = new Stage();
            	createDepStage.setTitle("Create New Employee");
            	createDepStage.setScene(createEmployeeScene);
            	createDepStage.initModality(Modality.APPLICATION_MODAL); 
            	createDepStage.initOwner(getView().getScene().getWindow());
            	createDepStage.showAndWait();
            	refreshEmployeeService();
            	getView().setDisable(false);
            }
        });
        
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Employee d = employeesTable.getSelectionModel().getSelectedItem();
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete employee " + d.getName() + "?", ButtonType.YES, ButtonType.NO);
            	alert.initModality(Modality.APPLICATION_MODAL);
            	alert.showAndWait();
            	if (alert.getResult() == ButtonType.YES)
            	{
            		employeeService.delete(d);
            		refreshEmployeeService();
            	}
            }
        });

    	employees = FXCollections.observableList(employeeService.getAll());
    	
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));

        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));

        departmentColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDepartment()));
        departmentColumn.setCellFactory(new Callback<TableColumn<Employee, Department>, TableCell<Employee, Department>>() {
            @Override
            public TableCell<Employee, Department> call(TableColumn<Employee, Department> param) {
                return new TableCell<Employee, Department>() {
                    @Override
                    protected void updateItem(Department department, boolean empty) {
                        super.updateItem(department, empty);
                        if (!empty) setText(department.getName());
                    }
                };
            }
        });
        
        getEmployeesService = new Service<List<Employee>>() {
            @Override
            protected Task<List<Employee>> createTask() {
                Task<List<Employee>> getEmployeesTask = new Task<List<Employee>>() {
                    @Override
                    protected List<Employee> call() throws Exception {
                        return employeeService.getAll();
                    }
                };
                return getEmployeesTask;
            }
        };

        refreshEmployeeService();
        
        getEmployeesService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                employees.setAll(getEmployeesService.getValue());
            }
        });

        employeesTable.setItems(employees);
    }
    
    void refreshEmployeeService()
    {
    	getEmployeesService.reset();
    	getEmployeesService.start();
    }
    
}
