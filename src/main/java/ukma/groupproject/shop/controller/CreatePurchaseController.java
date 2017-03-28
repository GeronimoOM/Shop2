package ukma.groupproject.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.PurchaseItem;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.PurchaseService;

@Component
@Scope("prototype")
public class CreatePurchaseController extends Controller {

	@FXML ListView<Item> allItemsList;
	@FXML ListView<PurchaseItem> addedItemsList;

	@FXML Button addButton;
	@FXML Button removeButton;
	@FXML Button createButton;
	
	@FXML TextField amountTextField;

    @Autowired private ItemService itemService;
    @Autowired private PurchaseService purchaseService;

    private ObservableList<Item> allItems;
    private ObservableList<PurchaseItem> addedItems;
    List<PurchaseItem> addedItemsData;
    
    private Service<List<Item>> getItemsService;

    @Override
    public void initialize() 
    {
    	allItems = FXCollections.observableList(itemService.getAll());
    	allItemsList.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
			@Override
			public ListCell<Item> call(ListView<Item> param) {
                return new ListCell<Item>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty) setText("[" + item.getAmount() + " left] " + item.getName());
                    }
                };
			}
        });

    	addedItemsData = new ArrayList<PurchaseItem>();
    	addedItems = FXCollections.observableList(addedItemsData);
    	addedItemsList.setCellFactory(param -> new ListCell<PurchaseItem>() {
    		@Override
    		    protected void updateItem(PurchaseItem item, boolean empty) {
    		        super.updateItem(item, empty);
    		        this.setText(item == null ? null : item.getAmount() + " of " + item.getItem().getName() + "");
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
            	 int amount = allItemsList.getSelectionModel().getSelectedItem().getAmount();
            	 if (amountTextField.getText().equals("") || Integer.valueOf(amountTextField.getText()) > amount)
            	 {
            		 Alert alert = new Alert(AlertType.ERROR, 
            				 amountTextField.getText().equals("") ? 
            				 "Please specify amount of items purchased" : "There's only " + amount + " items of that kind left!\nChoose lesser amount.", 
            				 ButtonType.OK);
            		 alert.initModality(Modality.APPLICATION_MODAL);
            		 alert.showAndWait();
            		 return;
            	 }
            		 
            	 for (PurchaseItem addedItem : addedItems)
            		 if (addedItem.getItem().equals(allItemsList.getSelectionModel().getSelectedItem()))
            		 {
            			 // only update added items list with new amount if item already added
            			 addedItem.setAmount(Integer.valueOf(amountTextField.getText()));
            			 return;
            		 }
            	 addedItems.add(new PurchaseItem(null, allItemsList.getSelectionModel().getSelectedItem(), Integer.valueOf(amountTextField.getText())));
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
            	 if (addedItemsData.isEmpty())
            	 {
            		 Alert alert = new Alert(AlertType.ERROR, "You are trying to create an ampty purchase!\nAdd some items please.", ButtonType.OK);
            		 alert.initModality(Modality.APPLICATION_MODAL);
            		 alert.showAndWait();
            	 }
            	 else
            	 {
            		 Purchase p = new Purchase();
            		 p.setDate(new Date());
            		 p.setItems(addedItemsData);
            		 // TODO: only logged in employees can add, pass logged in employee here
            		 p.setEmployee(null);
            		 purchaseService.persist(p);
            		 
            		 Stage stage = (Stage) createButton.getScene().getWindow();
            		 stage.close();
            	 }
            	 createButton.getParent().getParent().setDisable(false);
             }
         });

         // only numbers in salary field
         amountTextField.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 if (!newValue.matches("\\d*")) amountTextField.setText(newValue.replaceAll("[^\\d]", ""));
             }
         });
    }
    
}
