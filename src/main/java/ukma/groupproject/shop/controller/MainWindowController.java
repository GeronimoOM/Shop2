package ukma.groupproject.shop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.*;
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

    @Override
    public void initialize() {
        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        supplierIdTextField.setFocusTraversable(false);
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
