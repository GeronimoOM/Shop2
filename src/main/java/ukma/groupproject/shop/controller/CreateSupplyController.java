package ukma.groupproject.shop.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.model.view.ItemAmountPrice;
import ukma.groupproject.shop.service.OrderService;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.SupplyFactory;
import ukma.groupproject.shop.service.SupplyService;
import ukma.groupproject.shop.service.util.ShopBusinessException;
import ukma.groupproject.shop.view.AutoCompleteChoiceTextField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class CreateSupplyController extends Controller {

    @FXML private AutoCompleteChoiceTextField<Supplier> supplierTextField;

    @FXML private TableView<Order> addedOrdersTable;
    @FXML private TableColumn<Order, Date> addedOrdersDateColumn;
    @FXML private TableColumn<Order, Item> addedOrdersItemColumn;
    @FXML private TableColumn<Order, Integer> addedOrdersAmountColumn;
    @FXML private TableView<Order> supplierOrdersTable;
    @FXML private TableColumn<Order, Date> supplierOrdersDateColumn;
    @FXML private TableColumn<Order, Item> supplierOrdersItemColumn;
    @FXML private TableColumn<Order, Integer> supplierOrdersAmountColumn;
    @FXML private TableView<ItemAmountPrice> supplyTable;
    @FXML private TableColumn<ItemAmountPrice, Item> supplyItemColumn;
    @FXML private TableColumn<ItemAmountPrice, Integer> supplyAmountColumn;
    @FXML private TableColumn<ItemAmountPrice, Float> supplyPriceColumn;
    @FXML private Button addButton;
    @FXML private TextField priceTextField;
    @FXML private Button removeButton;
    @FXML private Button createButton;

    @Autowired private SupplierService supplierService;
    @Autowired private OrderService orderService;
    @Autowired private SupplyFactory supplyFactory;
    @Autowired private SupplyService supplyService;

    private ObservableList<Order> addedOrders;
    private ObservableList<Order> supplierOrders;
    private ObservableList<ItemAmountPrice> itemAmountPrices;
    private Map<Item, ItemAmountPrice> itemItemAmountPriceMap;

    private Service<List<Order>> getOrdersBySupplierService;

    private DateFormat dateFormat;

    private Supply supply;

    @Override
    public void initialize() {
        addedOrders = FXCollections.observableArrayList();
        supplierOrders = FXCollections.observableArrayList();
        itemAmountPrices = FXCollections.observableArrayList();

        itemItemAmountPriceMap = new HashMap<>();

        supplierTextField.setConverter(supplier -> supplier.getName());
        supplierTextField.getEntries().setAll(supplierService.getAll());

        addedOrdersDateColumn.prefWidthProperty().bind(addedOrdersTable.widthProperty().multiply(0.2));
        addedOrdersItemColumn.prefWidthProperty().bind(addedOrdersTable.widthProperty().multiply(0.6));
        addedOrdersAmountColumn.prefWidthProperty().bind(addedOrdersTable.widthProperty().multiply(0.2));
        supplierOrdersDateColumn.prefWidthProperty().bind(supplierOrdersTable.widthProperty().multiply(0.2));
        supplierOrdersItemColumn.prefWidthProperty().bind(supplierOrdersTable.widthProperty().multiply(0.6));
        supplierOrdersAmountColumn.prefWidthProperty().bind(supplierOrdersTable.widthProperty().multiply(0.2));
        supplyItemColumn.prefWidthProperty().bind(supplyTable.widthProperty().multiply(0.6));
        supplyAmountColumn.prefWidthProperty().bind(supplyTable.widthProperty().multiply(0.2));
        supplyPriceColumn.prefWidthProperty().bind(supplyTable.widthProperty().multiply(0.2));

        dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        Callback<TableColumn<Order, Date>, TableCell<Order, Date>> dateCellFactory = param -> new TableCell<Order, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if(!empty) {
                    setText(dateFormat.format(date));
                } else {
                    setText("");
                }
            }
        };
        Callback<TableColumn<Order, Item>, TableCell<Order, Item>> itemCellFactory = param -> new TableCell<Order, Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.getName());
                } else {
                    setText("");
                }
            }
        };
        Callback<TableColumn<Order, Integer>, TableCell<Order, Integer>> amountCellFactory =param -> new TableCell<Order, Integer>() {
            @Override
            protected void updateItem(Integer amount, boolean empty) {
                super.updateItem(amount, empty);
                if (!empty) {
                    setText(amount.toString());
                } else {
                    setText("");
                }
            }
        };
        addedOrdersDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        addedOrdersDateColumn.setCellFactory(dateCellFactory);
        addedOrdersItemColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getItem()));
        addedOrdersItemColumn.setCellFactory(itemCellFactory);
        addedOrdersAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        addedOrdersAmountColumn.setCellFactory(amountCellFactory);

        supplierOrdersDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        supplierOrdersDateColumn.setCellFactory(dateCellFactory);
        supplierOrdersItemColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getItem()));
        supplierOrdersItemColumn.setCellFactory(itemCellFactory);
        supplierOrdersAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        supplierOrdersAmountColumn.setCellFactory(amountCellFactory);

        supplyItemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        supplyItemColumn.setCellFactory(param -> new TableCell<ItemAmountPrice, Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.getName());
                } else {
                    setText("");
                }
            }
        });
        supplyAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        supplyAmountColumn.setCellFactory(param -> new TableCell<ItemAmountPrice, Integer>() {
            @Override
            protected void updateItem(Integer amount, boolean empty) {
                super.updateItem(amount, empty);
                if (!empty) {
                    setText(amount.toString());
                } else {
                    setText("");
                }
            }
        });
        supplyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplyPriceColumn.setCellFactory(param -> new TableCell<ItemAmountPrice, Float>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (!empty) {
                    setText(price.toString());
                } else {
                    setText("");
                }
            }
        });

        getOrdersBySupplierService = new Service<List<Order>>() {
            @Override
            protected Task<List<Order>> createTask() {
                return new Task<List<Order>>() {
                    @Override
                    protected List<Order> call() throws Exception {
                        return orderService.getActiveOrdersFor(supplierTextField.getSelected());
                    }
                };
            }
        };
        getOrdersBySupplierService.setOnSucceeded(event -> {
            supplierOrders.setAll(getOrdersBySupplierService.getValue());
        });

        supplierTextField.selectedProperty().addListener((observable, oldValue, newValue) -> {
            addedOrders.clear();
            supplierOrders.clear();
            priceTextField.clear();

            if(newValue != null) {
                getOrdersBySupplierService.reset();
                getOrdersBySupplierService.start();
            }
        });

        supplierOrdersTable.setItems(supplierOrders);
        addedOrdersTable.setItems(addedOrders);
        supplyTable.setItems(itemAmountPrices);

        addButton.setOnAction(event -> {
            Order order = supplierOrdersTable.getSelectionModel().getSelectedItem();
            ItemAmountPrice itemAmountPrice = itemItemAmountPriceMap.get(order.getItem());
            if(itemAmountPrice == null) {
                try {
                    Float price = Float.parseFloat(priceTextField.getText());
                    itemAmountPrice = new ItemAmountPrice(order.getItem(), order.getAmount(), price);
                    itemItemAmountPriceMap.put(order.getItem(), itemAmountPrice);
                    itemAmountPrices.add(itemAmountPrice);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid price");
                    alert.showAndWait();
                    return;
                }
            } else {
                itemAmountPrice.setAmount(itemAmountPrice.getAmount() + order.getAmount());
            }

            supplierOrdersTable.getItems().remove(order);
            supplierOrders.remove(order);
            addedOrders.add(order);
        });

        removeButton.setOnAction(event -> {
            Order order = addedOrdersTable.getSelectionModel().getSelectedItem();
            ItemAmountPrice itemAmountPrice = itemItemAmountPriceMap.get(order.getItem());
            itemAmountPrice.setAmount(itemAmountPrice.getAmount() - order.getAmount());
            if(itemAmountPrice.getAmount() == 0) {
                itemItemAmountPriceMap.remove(order.getItem());
                itemAmountPrices.remove(itemAmountPrice);
            }

            addedOrders.remove(order);
            supplierOrders.add(order);
        });

        createButton.setOnAction(event -> {
            try {
                supply = supplyFactory.create(addedOrders, itemItemAmountPriceMap.values().stream().collect(Collectors.toMap(
                        itemAmountPrice -> itemAmountPrice.getItem(),
                        itemAmountPrice -> itemAmountPrice.getPrice()
                )));
                supplyService.persist(supply);
                Stage stage = (Stage) getView().getScene().getWindow();
                stage.close();

            } catch (ShopBusinessException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }

        });
    }

    public Supply getSupply() {
        return supply;
    }

}
