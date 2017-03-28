package ukma.groupproject.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.service.DepartmentService;

@Component
@Scope("prototype")
public class DepartmentsTabController extends Controller {

    @FXML private TableView<Department> departmentsTable;
    @FXML private TableColumn<Department, String> nameColumn;
    
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private DepartmentService departmentService;

    private ObservableList<Department> departments;

    private Service<List<Department>> getDepartmentsService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    private CreateDepartmentController createDepartmentController;
    private Scene createDepartmentScene;

    @Override
    public void initialize() 
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	getView().setDisable(true);
            	createDepartmentController = (CreateDepartmentController) fxmlLoader.load("views/CreateDepartment.fxml");
            	createDepartmentScene = new Scene((Parent) createDepartmentController.getView());
            	createDepartmentScene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);

            	Stage createDepStage = new Stage();
            	createDepStage.setTitle("Create New Department");
            	createDepStage.setScene(createDepartmentScene);
            	createDepStage.initModality(Modality.APPLICATION_MODAL); 
            	createDepStage.initOwner(getView().getScene().getWindow());
            	createDepStage.showAndWait();
            	refreshDepartmentService();
            	getView().setDisable(false);
            }
        });
        
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Department d = departmentsTable.getSelectionModel().getSelectedItem();
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete department " + d.getName() + "?", ButtonType.YES, ButtonType.NO);
            	alert.initModality(Modality.APPLICATION_MODAL);
            	alert.showAndWait();
            	if (alert.getResult() == ButtonType.YES)
            	{
            		departmentService.delete(d);
            		refreshDepartmentService();
            	}
            }
        });

    	departments = FXCollections.observableList(departmentService.getAll());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        
        getDepartmentsService = new Service<List<Department>>() {
            @Override
            protected Task<List<Department>> createTask() {
                Task<List<Department>> getDepartmentsTask = new Task<List<Department>>() {
                    @Override
                    protected List<Department> call() throws Exception {
                        return departmentService.getAll();
                    }
                };
                return getDepartmentsTask;
            }
        };

        refreshDepartmentService();
        
        getDepartmentsService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                departments.setAll(getDepartmentsService.getValue());
            }
        });

        departmentsTable.setItems(
        		departments
        		);
    }
    
    void refreshDepartmentService()
    {
    	getDepartmentsService.reset();
    	getDepartmentsService.start();
    }
    
}
