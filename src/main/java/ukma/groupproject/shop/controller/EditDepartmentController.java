package ukma.groupproject.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.view.DepartmentView;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.impl.DepartmentViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class EditDepartmentController extends Controller {

    @FXML Button editButton;
    @FXML TextField nameTextField;

    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentViewMapper mapper;

    private Department department;
    private DepartmentView departmentView;

    @Override
    public void initialize() {
        editButton.setOnAction(e -> {
            if (nameTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Name cannot be empty!", ButtonType.OK);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
            } else {
                try {
                    departmentService.update(mapper.mapFrom(departmentView));
                    mapper.mapFrom(departmentView, department);
                    ((Stage) view.getScene().getWindow()).close();
                } catch(ShopBusinessException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            }
        });
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        departmentView = mapper.mapTo(department);
        nameTextField.setText(department.getName());
        departmentView.nameProperty().bind(nameTextField.textProperty());
    }
}
