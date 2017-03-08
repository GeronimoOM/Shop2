package ukma.groupproject.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.SupplierService;

@Component
public class NestedController extends Controller{

    @FXML
    private Label gridIdLabel;

    @FXML
    private Label gridNameLabel;

    @Autowired
    private SupplierService supplierService;

    private Supplier supplier;

    public void setSupplier(Supplier supplier) {
        if(supplier != null) {
            gridIdLabel.setText(String.valueOf(supplier.getId()));
            gridNameLabel.setText(supplier.getName());
        }
        else {
            gridIdLabel.setText("");
            gridNameLabel.setText("");
        }
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
