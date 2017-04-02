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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.SupplierService;

@Component
@Scope("prototype")
public class SuppliersTabController extends Controller {

    @FXML private TableView<Supplier> suppliersTable;
    @FXML private TableColumn<Supplier, String> nameColumn;
    
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private SpringFxmlLoader fxmlLoader;
    @Autowired private SupplierService supplierService;

    private ObservableList<Supplier> suppliers;
    private Service<List<Supplier>> getSuppliersService;

    private CreateSupplierController createSupplierController;

    @Override
    public void initialize() {
        nameColumn.prefWidthProperty().bind(suppliersTable.widthProperty());

        createButton.setOnAction(e -> {
            createSupplierController = (CreateSupplierController) fxmlLoader.load("views/CreateSupplier.fxml");
            createModal("Create Supplier", createSupplierController).showAndWait();

            if(createSupplierController.getSupplier() != null) {
                suppliers.add(createSupplierController.getSupplier());
            }
        });
        
        removeButton.setOnAction(e -> {
            Supplier s = suppliersTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete supplier " + s.getName() + "?", ButtonType.YES, ButtonType.NO);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES)
            {
                supplierService.delete(s);
                refreshSupplierService();
            }
        });

    	suppliers = FXCollections.observableList(supplierService.getAll());

        nameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        
        getSuppliersService = new Service<List<Supplier>>() {
            @Override
            protected Task<List<Supplier>> createTask() {
                Task<List<Supplier>> getSuppliersTask = new Task<List<Supplier>>() {
                    @Override
                    protected List<Supplier> call() throws Exception {
                        return supplierService.getAll();
                    }
                };
                return getSuppliersTask;
            }
        };

        refreshSupplierService();
        
        getSuppliersService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                suppliers.setAll(getSuppliersService.getValue());
            }
        });

        suppliersTable.setItems(suppliers);
        
    }
    
    void refreshSupplierService()
    {
        getSuppliersService.reset();
        getSuppliersService.start();
    }
    
}
