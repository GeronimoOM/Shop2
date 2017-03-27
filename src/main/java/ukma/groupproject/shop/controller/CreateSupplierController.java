package ukma.groupproject.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.SupplierService;

@Component
public class CreateSupplierController extends Controller {

	@FXML ListView<Item> allItemsList;
	@FXML ListView<Item> addedItemsList;

	@FXML Button addButton;
	@FXML Button removeButton;
	@FXML Button createButton;
	
	@FXML TextField nameTextField;

    @Autowired private ItemService itemService;
    @Autowired private SupplierService supplierService;

    private ObservableList<Item> allItems;
    private ObservableList<Item> addedItems;
    List<Item> addedItemsData;
    
    private Service<List<Item>> getItemsService;

    @Override
    public void initialize() 
    {
    	allItems = FXCollections.observableList(itemService.getAll());
    	allItemsList.setCellFactory(param -> new ListCell<Item>() {
    		@Override
    		    protected void updateItem(Item item, boolean empty) {
    		        super.updateItem(item, empty);
    		        this.setText(item == null ? null : item.getName());
    		    }
    		});

    	addedItemsData = new ArrayList<Item>();
    	addedItems = FXCollections.observableList(addedItemsData);
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
         getItemsService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
             @Override
             public void handle(WorkerStateEvent event) {
            	 allItems.setAll(getItemsService.getValue());
             }
         });

    	 allItemsList.setItems(allItems);
    	 addedItemsList.setItems(addedItems);
    	 
         addButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override public void handle(ActionEvent e) {
            	 if (addedItems.contains(allItemsList.getSelectionModel().getSelectedItem()))
            		 return;
            	 addedItems.add(allItemsList.getSelectionModel().getSelectedItem());
            	 allItemsList.getSelectionModel().clearSelection();
             }
         });

         removeButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override public void handle(ActionEvent e) {
            	 addedItems.remove(addedItemsList.getSelectionModel().getSelectedItem());
             }
         });

         createButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override public void handle(ActionEvent e) {
            	 createButton.getParent().getParent().setDisable(true);
            	 boolean alreadyContains = false;
            	 for (Supplier supp : supplierService.getAll())
            		 if (supp.getName().equals(nameTextField.getText())) alreadyContains = true;
            	 if (nameTextField.getText().equals("") || alreadyContains)
            	 {
            		 Alert alert = new Alert(AlertType.ERROR, alreadyContains ? "Such supplier already exists!" : "Name cannot be empty!", ButtonType.OK);
            		 alert.initModality(Modality.APPLICATION_MODAL);
            		 alert.showAndWait();
            	 }
            	 else
            	 {
            		 Supplier s = new Supplier();
            		 s.setName(nameTextField.getText());
            		 s.setItems(addedItemsData);
            		 supplierService.persist(s);
            		 
            		 Stage stage = (Stage) createButton.getScene().getWindow();
            		 stage.close();
            	 }
            	 createButton.getParent().getParent().setDisable(false);
             }
         });
    }
    
}
