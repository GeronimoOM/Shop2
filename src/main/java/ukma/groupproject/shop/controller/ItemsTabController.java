package ukma.groupproject.shop.controller;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.ItemService;

import java.util.List;

@Component
@Scope("prototype")
public class ItemsTabController extends Controller {

    @FXML private ChoiceBox<Department> departmentChoiceBox;
    @FXML private TableView<Item> itemsTable;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, Integer> amountColumn;
    @FXML private TableColumn<Item, Integer> minAmountColumn;
    @FXML private TableColumn<Item, Department> departmentColumn;

    @Autowired private ItemService itemService;
    @Autowired private DepartmentService departmentService;

    private ObservableList<Item> items;
    private ObservableList<Department> departments;

    private BooleanProperty isItemsTableReloading;
    private BooleanProperty isSingleDepartmentMode;

    private Service<List<Item>> getItemsService;

    private static final int DEPARTMENT_COLUMN_HIDE_ANIMATION_DURATION_MILLIS = 200;
    private Timeline departmentColumnHideAnimation;
    private Timeline departmentColumnShowAnimation;

    @Override
    public void initialize() {
        items = FXCollections.observableList(itemService.getAll());
        departments = FXCollections.observableList(departmentService.getAll());
        departments.add(0, null);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("amount"));
        minAmountColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minAmount"));
        departmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, Department>, ObservableValue<Department>>() {
            @Override
            public ObservableValue<Department> call(TableColumn.CellDataFeatures<Item, Department> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getDepartment());
            }
        });
        departmentColumn.setCellFactory(new Callback<TableColumn<Item, Department>, TableCell<Item, Department>>() {
            @Override
            public TableCell<Item, Department> call(TableColumn<Item, Department> param) {
                return new TableCell<Item, Department>() {
                    @Override
                    protected void updateItem(Department department, boolean empty) {
                        super.updateItem(department, empty);
                        if(!empty) {
                            setText(department.getName());
                        }
                    }
                };
            }
        });

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

        isItemsTableReloading = new SimpleBooleanProperty(false);
        itemsTable.disableProperty().bind(isItemsTableReloading);
        departmentChoiceBox.disableProperty().bind(isItemsTableReloading);

        isSingleDepartmentMode = new SimpleBooleanProperty(false);
        isSingleDepartmentMode.bind(departmentChoiceBox.getSelectionModel().selectedItemProperty().isNotNull());
        departmentChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, final Department newValue) {
                getItemsService.reset();
                getItemsService.start();
            }
        });
        departmentChoiceBox.setItems(departments);
        departmentChoiceBox.getSelectionModel().select(0);

        initializeServices();
        initializeAnimations();

        itemsTable.setItems(items);
    }

    private void initializeServices() {
        getItemsService = new Service<List<Item>>() {
            @Override
            protected Task<List<Item>> createTask() {
                Task<List<Item>> getItemsTask = new Task<List<Item>>() {
                    @Override
                    protected List<Item> call() throws Exception {
                        return departmentChoiceBox.getValue() == null ? itemService.getAll():
                                itemService.getByDepartment(departmentChoiceBox.getValue());
                    }
                };
                return getItemsTask;
            }
        };
        getItemsService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                items.setAll(getItemsService.getValue());
            }
        });
        getItemsService.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldState, Worker.State newState) {
                switch (newState) {
                    case SCHEDULED:
                        isItemsTableReloading.setValue(Boolean.TRUE);
                        break;
                    case CANCELLED: case FAILED: case SUCCEEDED:
                        isItemsTableReloading.setValue(Boolean.FALSE);
                }
            }
        });
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