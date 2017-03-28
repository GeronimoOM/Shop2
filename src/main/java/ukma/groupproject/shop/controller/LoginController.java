package ukma.groupproject.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.service.EmployeeService;
import ukma.groupproject.shop.view.AutoCompleteChoiceTextField;

@Component
@Scope("prototype")
public class LoginController extends Controller {

    @FXML private AutoCompleteChoiceTextField<Employee> loginChoiceTextField;
    @FXML private Button loginButton;

    @Autowired private EmployeeService employeeService;

    private Employee employee;

    @Override
    public void initialize() {
        loginChoiceTextField.setConverter(Employee::getName);
        loginChoiceTextField.getEntries().setAll(employeeService.getAll());
        loginChoiceTextField.selectedProperty().addListener((observable, oldValue, newValue) -> {
            employee = newValue;
        });
        loginButton.disableProperty().bind(loginChoiceTextField.selectedProperty().isNull());
        loginButton.setOnAction(event -> {
            ((Stage) getView().getScene().getWindow()).close();
        });
    }

    public Employee getEmployee() {
        return employee;
    }
}
