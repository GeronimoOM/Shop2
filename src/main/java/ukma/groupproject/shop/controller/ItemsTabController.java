package ukma.groupproject.shop.controller;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.ItemService;

import java.util.List;

@Component
public class ItemsTabController extends Controller {

    @FXML private ChoiceBox<Department> departmentChoiceBox;
    @FXML private TableView<Item> itemsTable;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, Integer> amountColumn;
    @FXML private TableColumn<Item, Integer> minAmountColumn;
    @FXML private TableColumn<Item, String> departmentColumn;

    @Autowired private ItemService itemService;
    @Autowired private DepartmentService departmentService;

    private BooleanProperty isSingleDepartmentMode;
    private ObservableList<Item> items;
    private ObservableList<Department> departments;

    private Service<List<Item>> getAllItemsService;
    private Service<List<Item>> getItemsByDepartmentService;
    private EventHandler<WorkerStateEvent> onGetItemsSucceeded;

    private static final int DEPARTMENT_COLUMN_HIDE_ANIMATION_DURATION_MILLIS = 200;
    private Timeline departmentColumnHideAnimation;
    private Timeline departmentColumnShowAnimation;

    @Override
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("amount"));
        minAmountColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minAmount"));
        departmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getDepartment().getName());
            }
        });

        items = FXCollections.observableList(itemService.getAll());
        itemsTable.setItems(items);

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

        isSingleDepartmentMode = new SimpleBooleanProperty(false);
        departmentChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, final Department newValue) {
                onDepartmentSelectionChanged(newValue);
            }
        });
        departmentChoiceBox.setItems(departments);
        departmentChoiceBox.getSelectionModel().select(0);

        initializeServices();
        initializeAnimations();
    }

    private void initializeServices() {
        onGetItemsSucceeded = new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                items.setAll((List<Item>) event.getSource().getValue());
                departmentChoiceBox.setDisable(false);
            }
        };

        getAllItemsService = new Service<List<Item>>() {
            @Override
            protected Task<List<Item>> createTask() {
                Task<List<Item>> getAllItemsTask = new Task<List<Item>>() {
                    @Override
                    protected List<Item> call() throws Exception {
                        return itemService.getAll();
                    }
                };
                getAllItemsTask.setOnSucceeded(onGetItemsSucceeded);
                return getAllItemsTask;
            }
        };

        getItemsByDepartmentService = new Service<List<Item>>() {
            @Override
            protected Task<List<Item>> createTask() {
                Task<List<Item>> getItemsByDepartmentTask = new Task<List<Item>>() {
                    @Override
                    protected List<Item> call() throws Exception {
                        return itemService.getByDepartment(departmentChoiceBox.getValue());
                    }
                };
                getItemsByDepartmentTask.setOnSucceeded(onGetItemsSucceeded);
                return getItemsByDepartmentTask;
            }
        };
    }

    private void onDepartmentSelectionChanged(final Department department) {
        if(department == null) {
            departmentChoiceBox.setDisable(true);
            getAllItemsService.reset();
            getAllItemsService.start();
            isSingleDepartmentMode.setValue(Boolean.FALSE);
        } else {
            departmentChoiceBox.setDisable(true);
            getItemsByDepartmentService.reset();
            getItemsByDepartmentService.start();
            isSingleDepartmentMode.setValue(Boolean.TRUE);
        }
    }

    private void initializeAnimations() {
        nameColumn.prefWidthProperty().bind(itemsTable.widthProperty()
                .subtract(amountColumn.widthProperty()
                        .add(minAmountColumn.widthProperty())
                        .add(departmentColumn.widthProperty())));

        departmentColumn.visibleProperty().bind(departmentColumn.widthProperty().isNotEqualTo(0));

        departmentColumnHideAnimation = new Timeline(new KeyFrame(Duration.millis(DEPARTMENT_COLUMN_HIDE_ANIMATION_DURATION_MILLIS),
                new KeyValue(departmentColumn.prefWidthProperty(), 0)));

        departmentColumnShowAnimation = new Timeline(new KeyFrame(Duration.millis(DEPARTMENT_COLUMN_HIDE_ANIMATION_DURATION_MILLIS),
                new KeyValue(departmentColumn.prefWidthProperty(), 300)));

        isSingleDepartmentMode.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    departmentColumnHideAnimation.play();
                } else {
                    departmentColumnShowAnimation.play();
                }
            }
        });
    }
}