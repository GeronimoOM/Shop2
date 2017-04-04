package ukma.groupproject.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.view.DepartmentView;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.impl.DepartmentViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class DepartmentsTabController extends Controller {

    @FXML private TableView<DepartmentView> departmentsTable;
    @FXML private TableColumn<DepartmentView, String> nameColumn;
    
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentViewMapper mapper;

    private ObservableList<DepartmentView> departments;

    private Service<List<DepartmentView>> getDepartmentsService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    @Override
    public void initialize() {
        createButton.setOnAction(e -> {
            CreateDepartmentController createDepartmentController = (CreateDepartmentController) fxmlLoader.load("CreateDepartment");
            createModal("Create Department", createDepartmentController).showAndWait();

            if(createDepartmentController.getDepartment() != null) {
                departments.add(mapper.mapTo(createDepartmentController.getDepartment()));
            }
        });

        editButton.setOnAction(e -> {
            if(departmentsTable.getSelectionModel().getSelectedItem() != null) {
                EditDepartmentController editDepartmentController = (EditDepartmentController) fxmlLoader.load("EditDepartment");
                DepartmentView departmentView = departmentsTable.getSelectionModel().getSelectedItem();
                editDepartmentController.setDepartment(mapper.mapFrom(departmentView));
                createModal("Edit Department", editDepartmentController).showAndWait();

                mapper.mapTo(editDepartmentController.getDepartment(), departmentView);
            }
        });
        
        removeButton.setOnAction(e -> {
            DepartmentView department = departmentsTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete department " + department.getName() + " ?", ButtonType.YES, ButtonType.NO);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    departmentService.delete(mapper.mapFrom(department));
                    departments.remove(department);
                } catch (ShopBusinessException ex) {
                    Alert error = new Alert(AlertType.ERROR, ex.getMessage());
                    error.initModality(Modality.APPLICATION_MODAL);
                    error.showAndWait();
                }
            }
        });

    	departments = FXCollections.observableArrayList();
        departmentsTable.setItems(departments);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        getDepartmentsService = new Service<List<DepartmentView>>() {
            @Override
            protected Task<List<DepartmentView>> createTask() {
                return new Task<List<DepartmentView>>() {
                    @Override
                    protected List<DepartmentView> call() throws Exception {
                        return departmentService.getAll().stream().map(dep -> mapper.mapTo(dep)).collect(Collectors.toList());
                    }
                };
            }
        };
        getDepartmentsService.setOnSucceeded(event ->
            departments.setAll(getDepartmentsService.getValue()));
        getDepartmentsService.start();
    }
    
}
