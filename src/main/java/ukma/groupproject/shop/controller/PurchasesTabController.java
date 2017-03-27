package ukma.groupproject.shop.controller;

import java.util.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.PurchaseService;

@Component
public class PurchasesTabController extends Controller {

    @FXML private TableView<Purchase> purchasesTable;
    @FXML private TableColumn<Purchase, Date> dateColumn;
    @FXML private TableColumn<Purchase, Item> itemsColumn;
    
    @FXML private Button createButton;
    @FXML private Button removeButton;

    @Autowired private PurchaseService purchaseService;

    private ObservableList<Purchase> purchases;

    private Service<List<Purchase>> getPurchasesService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    private CreatePurchaseController createPurchaseController;
    private Scene createPurchaseScene;

    @Override
    public void initialize() 
    {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	getView().setDisable(true);
                createPurchaseController = (CreatePurchaseController) fxmlLoader.load("/views/CreatePurchase.fxml");
            	createPurchaseScene = new Scene((Parent) createPurchaseController.getView());
            	createPurchaseScene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);

            	Stage createPurchaseStage = new Stage();
            	createPurchaseStage.setTitle("Create New Purchase");
            	createPurchaseStage.setScene(createPurchaseScene);
            	createPurchaseStage.initModality(Modality.APPLICATION_MODAL); 
            	createPurchaseStage.initOwner(getView().getScene().getWindow());
            	createPurchaseStage.showAndWait();
        		refreshPurchaseService();
            	getView().setDisable(false);
            }
        });
        
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Purchase p = purchasesTable.getSelectionModel().getSelectedItem();
            	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this purchase?", ButtonType.YES, ButtonType.NO);
            	alert.initModality(Modality.APPLICATION_MODAL);
            	alert.showAndWait();
            	if (alert.getResult() == ButtonType.YES)
            	{
            		purchaseService.delete(p);
            		refreshPurchaseService();
            	}
            }
        });

    	purchases = FXCollections.observableList(purchaseService.getAll());

        dateColumn.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("date"));
    	    
        getPurchasesService = new Service<List<Purchase>>() {
            @Override
            protected Task<List<Purchase>> createTask() {
                Task<List<Purchase>> getPurchasesTask = new Task<List<Purchase>>() {
                    @Override
                    protected List<Purchase> call() throws Exception {
                        return purchaseService.getAll();
                    }
                };
                return getPurchasesTask;
            }
        };

        refreshPurchaseService();
        
        getPurchasesService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                purchases.setAll(getPurchasesService.getValue());
            }
        });

        purchasesTable.setItems(purchases);
    }
    
    void refreshPurchaseService()
    {
        getPurchasesService.reset();
        getPurchasesService.start();
    }    
}
