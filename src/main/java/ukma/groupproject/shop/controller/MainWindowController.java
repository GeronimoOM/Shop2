package ukma.groupproject.shop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MainWindowController extends Controller {

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private TextField supplierIdTextField;

    @FXML
    private Button findSupplierButton;

    @FXML
    private TextField supplierNameTextField;

    @FXML
    private NestedController nestedController;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private OrderFactory orderFactory;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SupplyFactory supplyFactory;

    @Override
    public void initialize() {
        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        supplierIdTextField.setFocusTraversable(false);

        Employee employee = employeeService.get(1L);
        Supplier supplier = supplierService.get(17L);
        Item item1 = itemService.get(1L);
        final Item item2 = itemService.get(2L);

        Order order1 = orderFactory.create(employee, supplier, item1, 3);
        Order order2 = orderFactory.create(employee, supplier, item2, 3);
        orderService.persist(order1);
        orderService.persist(order2);

        Map priceMap = new HashMap<>();
        priceMap.put(item1, 20f);
        priceMap.put(item2, 10f);
        Supply supply = supplyFactory.create(Arrays.asList(order1, order2), priceMap);
        supplyService.persist(supply);
    }

    public void onFindSupplierButton() {
        Supplier supplier = null;
        try {
            Long supplierId = Long.parseLong(supplierIdTextField.getText());
            supplier = supplierService.get(supplierId);

            if(supplier != null) {
                supplierNameTextField.setText(supplier.getName());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Supplier with id %d doesn't exist", supplierId));
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Supplier id must be a natural number");
            alert.showAndWait();
        }
        nestedController.setSupplier(supplier);
    }

}
