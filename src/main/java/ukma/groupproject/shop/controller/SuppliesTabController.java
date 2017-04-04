package ukma.groupproject.shop.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.SupplyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class SuppliesTabController extends Controller {

    @FXML private TableView<Supply> suppliesTable;
    @FXML private TableColumn<Supply, Date> dateColumn;
    @FXML private TableColumn<Supply, Supplier> supplierColumn;
    @FXML private TableColumn<Supply, List<SupplyItem>> itemsColumn;

    @FXML private Button createButton;
    @FXML private Button removeButton;

    @Autowired private SupplyService supplyService;
    @Autowired private SpringFxmlLoader fxmlLoader;

    private ObservableList<Supply> supplies;
    private Service<List<Supply>> getSuppliesService;

    private DateFormat dateFormat;

    @Override
    public void initialize() {
        dateColumn.prefWidthProperty().bind(suppliesTable.widthProperty().multiply(0.2));
        supplierColumn.prefWidthProperty().bind(suppliesTable.widthProperty().multiply(0.3));
        itemsColumn.prefWidthProperty().bind(suppliesTable.widthProperty().multiply(0.5));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date") );
        dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        dateColumn.setCellFactory(param -> new TableCell<Supply, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty ? "" : dateFormat.format(date));
            }
        });
        supplierColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getSupplier()));
        supplierColumn.setCellFactory(param -> new TableCell<Supply, Supplier>() {
            @Override
            protected void updateItem(Supplier supplier, boolean empty) {
                super.updateItem(supplier, empty);
                if(!empty) {
                    setText(supplier.getName());
                }
            }
        });
        itemsColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getItems()));
        itemsColumn.setCellFactory(param -> new TableCell<Supply, List<SupplyItem>>() {
            @Override
            protected void updateItem(List<SupplyItem> items, boolean empty) {
                super.updateItem(items, empty);
                if (!empty) {
                    setText(items.stream().map(i -> i.getItem().getName()).collect(Collectors.joining(", ")));
                }
            }
        });

        supplies = FXCollections.observableList(supplyService.getAll());

        getSuppliesService = new Service<List<Supply>>() {
            @Override
            protected Task<List<Supply>> createTask() {
                return new Task<List<Supply>>() {
                    @Override
                    protected List<Supply> call() throws Exception {
                        return supplyService.getAll();
                    }
                };
            }
        };
        getSuppliesService.setOnSucceeded(event -> supplies.setAll(getSuppliesService.getValue()));

        suppliesTable.setItems(supplies);

        createButton.setOnAction(event -> {
            CreateSupplyController createSupplyController = (CreateSupplyController) fxmlLoader.load("views/CreateSupply.fxml");
            createModal("Create Supply", createSupplyController).showAndWait();

            Supply supply = createSupplyController.getSupply();
            if(supply != null) {
                supplies.add(supply);
            }
        });
    }
}
