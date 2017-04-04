package ukma.groupproject.shop.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ukma.groupproject.shop.app.SpringJavaFxApplication;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.view.DepartmentView;
import ukma.groupproject.shop.model.view.ItemView;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.impl.DepartmentViewMapper;
import ukma.groupproject.shop.service.impl.ItemViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class ItemsTabController extends Controller {

    @FXML private TextField nameTextField;
    @FXML private CheckBox runningOutCheckBox;
    @FXML private ChoiceBox<Department> departmentChoiceBox;
    @FXML private TableView<ItemView> itemsTable;
    @FXML private TableColumn<ItemView, String> nameColumn;
    @FXML private TableColumn<ItemView, Float> priceColumn;
    @FXML private TableColumn<ItemView, Integer> amountColumn;
    @FXML private TableColumn<ItemView, Integer> minAmountColumn;
    @FXML private TableColumn<ItemView, Department> departmentColumn;

    @FXML private Button orderButton;
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    @Autowired private MainController mainController;
    @Autowired private ItemService itemService;
    @Autowired private DepartmentService departmentService;
    @Autowired private ItemViewMapper itemViewMapper;

    private ObservableList<ItemView> items;
    private ObservableList<ItemView> filteredItems;
    private ObservableList<Department> departments;
    private BooleanProperty filtersChanged;
    private Predicate<ItemView> nameFilter;
    private Predicate<ItemView> runningOutFilter;
    private Predicate<ItemView> departmentFilter;

    private Service<List<Item>> getItemsService;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    @Override
    public void initialize() {
        items = FXCollections.observableArrayList();
        itemsTable.setItems(items);

        nameColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.28));
        priceColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.2));
        amountColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.15));
        minAmountColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.15));
        departmentColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.2));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        minAmountColumn.setCellValueFactory(new PropertyValueFactory<>("minAmount"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        departmentColumn.setCellFactory(param -> new TableCell<ItemView, Department>() {
            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);
                setText(empty ? "" : department.getName());
            }
        });

        departments = FXCollections.observableList(departmentService.getAll());
        departments.add(0, null);
        departmentChoiceBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department == null ? "All departments" : department.getName();
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });

        filtersChanged = new SimpleBooleanProperty(false);
        filtersChanged.addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                itemsTable.setItems(getFilteredItems());
                filtersChanged.set(false);
            }
        });

        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            nameFilter = newValue == "" ? null : itemView -> itemView.getName().startsWith(newValue);
            filtersChanged.set(true);
        });

        runningOutCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            runningOutFilter = !newValue ? null: itemView -> itemView.getAmount() < itemView.getMinAmount();
            filtersChanged.set(true);
        });

        departmentChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            departmentFilter = newValue == null ? null: itemView -> itemView.getDepartment().equals(newValue);
            filtersChanged.set(true);
        });

        departmentChoiceBox.setItems(departments);
        departmentChoiceBox.getSelectionModel().select(0);

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
        getItemsService.setOnSucceeded(event ->
                items.setAll(getItemsService.getValue().stream().map(item -> itemViewMapper.mapTo(item)).collect(Collectors.toList())));
        getItemsService.start();

        orderButton.disableProperty().bind(mainController.employeeProperty().isNull());
        orderButton.setOnAction(event -> {
            if(itemsTable.getSelectionModel().getSelectedItem() != null) {
                CreateOrderController createOrderController = (CreateOrderController) fxmlLoader.load("CreateOrder");
                createOrderController.setItem(itemViewMapper.mapFrom(itemsTable.getSelectionModel().getSelectedItem()));
                createModal("Create Order", createOrderController).showAndWait();

                if (createOrderController.getOrder() != null) {
                    mainController.getOrdersTabController().getOrders().add(createOrderController.getOrder());
                }
            }
        });

        createButton.setOnAction(event -> {
            CreateItemController createItemController = (CreateItemController) fxmlLoader.load("CreateItem");
            createModal("Create Item", createItemController).showAndWait();

            if(createItemController.getItem() != null) {
                items.add(itemViewMapper.mapTo(createItemController.getItem()));
            }
        });

        editButton.setOnAction(event -> {
            if(itemsTable.getSelectionModel().getSelectedItem() != null) {
                EditItemController editItemController = (EditItemController) fxmlLoader.load("EditItem");
                ItemView itemView = itemsTable.getSelectionModel().getSelectedItem();
                editItemController.setItem(itemViewMapper.mapFrom(itemView));
                createModal("Edit Department", editItemController).showAndWait();

                itemViewMapper.mapTo(editItemController.getItem(), itemView);
            }
        });

        removeButton.setOnAction(event -> {
            ItemView item = itemsTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete item " + item.getName() + " ?", ButtonType.YES, ButtonType.NO);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    itemService.delete(itemViewMapper.mapFrom(item));
                    items.remove(item);
                } catch (ShopBusinessException ex) {
                    Alert error = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    error.initModality(Modality.APPLICATION_MODAL);
                    error.showAndWait();
                }
            }
        });
    }

    private ObservableList<ItemView> getFilteredItems() {
        ObservableList<ItemView> filteredItems = new FilteredList<>(items);
        if(nameFilter != null) {
            filteredItems = filteredItems.filtered(nameFilter);
        }
        if(runningOutCheckBox.isSelected()) {
            filteredItems = filteredItems.filtered(runningOutFilter);
        }
        if(departmentFilter != null) {
            filteredItems = filteredItems.filtered(departmentFilter);
        }
        return filteredItems;
    }
}