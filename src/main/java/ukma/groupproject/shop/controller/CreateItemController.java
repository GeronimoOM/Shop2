package ukma.groupproject.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
public class CreateItemController extends Controller {

    @FXML private TextField nameTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField minAmountTextField;
    @FXML private ChoiceBox<Department> departmentChoiceBox;
    @FXML private Button createButton;

    @Autowired private ItemService itemService;
    @Autowired private ItemViewMapper itemViewMapper;
    @Autowired private DepartmentService departmentService;

    private ObservableList<Department> departments;
    private Item item;

    @Override
    public void initialize() {
        ItemView itemView = new ItemView();
        itemView.nameProperty().bind(nameTextField.textProperty());
        itemView.departmentProperty().bind(departmentChoiceBox.getSelectionModel().selectedItemProperty());
        createButton.setOnAction(e -> {
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
                if(itemView.getDepartment() == null) {
                    showErrorAlert("Item must belong to a department");
                    return;
                }

                try {
                    item = itemViewMapper.mapFrom(itemView);
                    itemService.persist(item);
                    ((Stage) view.getScene().getWindow()).close();
                } catch(ShopBusinessException ex) {
                    item = null;
                    showErrorAlert(ex.getMessage());
                }
            }
        });

        departmentChoiceBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department.getName();
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });

        departments = FXCollections.observableList(departmentService.getAll());
        departmentChoiceBox.setItems(departments);
    }

    public Item getItem() {
        return item;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
