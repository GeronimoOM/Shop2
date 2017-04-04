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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.model.view.SupplierView;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.SupplierFactory;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class EditSupplierController extends Controller {

    @FXML TextField nameTextField;

    @FXML ListView<Item> allItemsList;
    @FXML ListView<Item> addedItemsList;

    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button createButton;

    @Autowired private ItemService itemService;
    @Autowired private SupplierService supplierService;
    @Autowired private SupplierFactory supplierFactory;

    private ObservableList<Item> allItems;
    private ObservableList<Item> addedItems;

    private Service<List<Item>> getItemsService;

    private Supplier supplier;
    private SupplierView supplierView;

    @Override
    public void initialize() {
        supplier = null;

        allItems = FXCollections.observableList(itemService.getAll());
        allItemsList.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                this.setText(item == null ? null : item.getName());
            }
        });

        addedItems = FXCollections.observableArrayList();
        addedItemsList.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                this.setText(item == null ? null : item.getName());
            }
        });

        getItemsService = new Service<List<Item>>() {
            @Override
            protected Task<List<Item>> createTask() {
                Task<List<Item>> getItemsTask = new Task<List<Item>>() {
                    @Override
                    protected List<Item> call() throws Exception {
                        return itemService.getAll();
                    }
                };
                return getItemsTask;
            }
        };
        getItemsService.setOnSucceeded(event -> allItems.setAll(getItemsService.getValue()));

        allItemsList.setItems(allItems);
        addedItemsList.setItems(addedItems);

        addButton.setOnAction(e -> {
            if (addedItems.contains(allItemsList.getSelectionModel().getSelectedItem()))
                return;
            addedItems.add(allItemsList.getSelectionModel().getSelectedItem());
            allItemsList.getSelectionModel().clearSelection();
        });

        removeButton.setOnAction(e -> addedItems.remove(addedItemsList.getSelectionModel().getSelectedItem()));

        createButton.setOnAction(e -> {
            createButton.getParent().getParent().setDisable(true);
            if (nameTextField.getText().isEmpty()) {
                showErrorAlert("Name cannot be empty!");
            } else {
                try {
                    Supplier supplier = supplierFactory.create(nameTextField.getText(), addedItems);
                    supplierService.persist(supplier);

                    this.supplier = supplier;

                    Stage stage = (Stage) createButton.getScene().getWindow();
                    stage.close();
                } catch (ShopBusinessException ex) {
                    showErrorAlert(ex.getMessage());
                }
            }
            createButton.getParent().getParent().setDisable(false);
        });
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
