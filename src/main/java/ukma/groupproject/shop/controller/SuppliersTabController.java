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
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.model.view.DepartmentView;
import ukma.groupproject.shop.model.view.SupplierView;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.impl.SupplierViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class SuppliersTabController extends Controller {

    @FXML private TableView<SupplierView> suppliersTable;
    @FXML private TableColumn<SupplierView, String> nameColumn;

    @FXML private TextField nameTextField;
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private SpringFxmlLoader fxmlLoader;
    @Autowired private SupplierService supplierService;
    @Autowired private SupplierViewMapper supplierViewMapper;

    private ObservableList<SupplierView> suppliers;
    private Service<List<SupplierView>> getSuppliersService;

    private CreateSupplierController createSupplierController;

    @Override
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == "") {
                suppliersTable.setItems(suppliers);
            } else {
                suppliersTable.setItems(suppliers.filtered(supplierView -> supplierView.getName().startsWith(newValue)));
            }
        });

        createButton.setOnAction(e -> {
            createSupplierController = (CreateSupplierController) fxmlLoader.load("CreateSupplier");
            createModal("Create Supplier", createSupplierController).showAndWait();

            if(createSupplierController.getSupplier() != null) {
                suppliers.add(supplierViewMapper.mapTo(createSupplierController.getSupplier()));
            }
        });

        editButton.setOnAction(event -> {
            if(suppliersTable.getSelectionModel().getSelectedItem() != null) {
                EditSupplierController editSupplierController = (EditSupplierController) fxmlLoader.load("EditSupplier");
                SupplierView supplierView = suppliersTable.getSelectionModel().getSelectedItem();
                editSupplierController.setSupplier(supplierViewMapper.mapFrom(supplierView));
                createModal("Edit Supplier", editSupplierController).showAndWait();

                supplierViewMapper.mapTo(editSupplierController.getSupplier(), supplierView);
            }
        });
        
        removeButton.setOnAction(e -> {
            SupplierView supplierView = suppliersTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete supplier " + supplierView.getName() + " ?", ButtonType.YES, ButtonType.NO);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    supplierService.delete(supplierViewMapper.mapFrom(supplierView));
                    suppliers.remove(supplierView);
                } catch (ShopBusinessException ex) {
                    Alert error = new Alert(AlertType.ERROR, ex.getMessage());
                    error.initModality(Modality.APPLICATION_MODAL);
                    error.showAndWait();
                }
            }
        });

        suppliers = FXCollections.observableArrayList();
        suppliersTable.setItems(suppliers);
        getSuppliersService = new Service<List<SupplierView>>() {
            @Override
            protected Task<List<SupplierView>> createTask() {
                return new Task<List<SupplierView>>() {
                    @Override
                    protected List<SupplierView> call() throws Exception {
                        return supplierService.getAll().stream().map(
                                supplier -> supplierViewMapper.mapTo(supplier)).collect(Collectors.toList());
                    }
                };
            }
        };

        getSuppliersService.setOnSucceeded(event -> suppliers.setAll(getSuppliersService.getValue()));
        getSuppliersService.start();
    }
    
}
