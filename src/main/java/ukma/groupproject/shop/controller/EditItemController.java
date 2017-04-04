package ukma.groupproject.shop.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.view.ItemView;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.impl.ItemViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class EditItemController extends Controller {

    @FXML private TextField nameTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField minAmountTextField;
    @FXML private TextField departmentTextField;
    @FXML private Button editButton;

    @Autowired private ItemService itemService;
    @Autowired private ItemViewMapper itemViewMapper;
    @Autowired private DepartmentService departmentService;

    private ObservableList<Department> departments;
    private Item item;
    private ItemView itemView;

    @Override
    public void initialize() {
        editButton.setOnAction(e -> {
            if (itemView.getName().equals("")) {
                showErrorAlert("Name cannot be empty");
            } else {
                try {
                    itemView.setPrice(Float.parseFloat(priceTextField.getText()));
                } catch (NumberFormatException ex) {
                    showErrorAlert("Not a valid price");
                    return;
                }
                try {
                    itemView.setMinAmount(Integer.parseInt(minAmountTextField.getText()));
                } catch (NumberFormatException ex) {
                    showErrorAlert("Not a valid minimum amount");
                    return;
                }

                try {
                    itemService.update(itemViewMapper.mapFrom(itemView));
                    itemViewMapper.mapFrom(itemView, item);
                    ((Stage) view.getScene().getWindow()).close();
                } catch(ShopBusinessException ex) {
                    showErrorAlert(ex.getMessage());
                }
            }
        });

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        itemView = itemViewMapper.mapTo(item);
        nameTextField.setText(item.getName());
        priceTextField.setText(String.valueOf(item.getPrice()));
        minAmountTextField.setText(String.valueOf(item.getMinAmount()));
        departmentTextField.setText(item.getDepartment().getName());

        itemView.nameProperty().bind(nameTextField.textProperty());
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
