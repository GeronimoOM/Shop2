package ukma.groupproject.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.view.DepartmentView;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.impl.DepartmentViewMapper;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Component
@Scope("prototype")
public class CreateDepartmentController extends Controller {

	@FXML Button createButton;
	@FXML TextField nameTextField;

    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentViewMapper mapper;

    private Department department;

    @Override
    public void initialize() {
        DepartmentView departmentView = new DepartmentView();
        departmentView.nameProperty().bind(nameTextField.textProperty());
    	createButton.setOnAction(e -> {
            if (nameTextField.getText().equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Name cannot be empty!", ButtonType.OK);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
            } else {
                try {
                    department = mapper.mapFrom(departmentView);
                    departmentService.persist(department);
                    ((Stage) view.getScene().getWindow()).close();
                } catch(ShopBusinessException ex) {
                    department = null;
                    Alert alert = new Alert(AlertType.ERROR, ex.getMessage());
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            }
    	});
    }

    public Department getDepartment() {
        return department;
    }
}
