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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.service.ItemService;

@Component
public class CreateSupplierController extends Controller {

	@FXML ListView<Item> allItemsList;
	@FXML ListView<Item> addedItemsList;

	@FXML Button addButton;
	@FXML Button removeButton;
	@FXML Button createButton;

    @Autowired private ItemService itemService;

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
            	 addedItems.add(allItemsList.getSelectionModel().getSelectedItem());
             }
         });

         removeButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override public void handle(ActionEvent e) {
            	 addedItems.remove(addedItemsList.getSelectionModel().getSelectedItem());
             }
         });
    }
    
}
