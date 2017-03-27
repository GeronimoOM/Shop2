package ukma.groupproject.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.EmployeeService;

@Component
public class CreateEmployeeController extends Controller {

	@FXML Button createButton;
	
	@FXML TextField nameTextField;
	@FXML TextField salaryTextField;

    @FXML private ChoiceBox<Department> departmentChoiceBox;
    private ObservableList<Department> departments;

    @Autowired private EmployeeService employeeService;
    @Autowired private DepartmentService departmentService;

    @Override
    public void initialize() 
    {
    	createButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			createButton.getParent().getParent().setDisable(true);
            	boolean alreadyContains = false;
            	for (Employee employee : employeeService.getAll())
            		if (employee.getName().equals(nameTextField.getText())) alreadyContains = true;
            	if (departmentChoiceBox.getSelectionModel().getSelectedItem() == null ||
            			salaryTextField.getText().equals("") || 
            			nameTextField.getText().equals("") || 
            			alreadyContains)
            	{
            		Alert alert = new Alert(AlertType.ERROR, "Invalid information", ButtonType.OK);
            		alert.initModality(Modality.APPLICATION_MODAL);
            		alert.showAndWait();
            	}
            	else
            	{
            		Employee emp = new Employee();
            		emp.setName(nameTextField.getText());
            		emp.setSalary(Integer.valueOf(salaryTextField.getText()));
            		emp.setDepartment(departmentChoiceBox.getSelectionModel().getSelectedItem());
            		employeeService.persist(emp);
            		 
            		Stage stage = (Stage) createButton.getScene().getWindow();
            		stage.close();
            	}
            	createButton.getParent().getParent().setDisable(false);
            }
        });
    	
        departmentChoiceBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                return department == null ? "-- Choose Department --" : department.getName();
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });

        departments = FXCollections.observableList(departmentService.getAll());
        departments.add(0, null);
        departmentChoiceBox.setItems(departments);
        departmentChoiceBox.getSelectionModel().select(0);
        
        // only numbers in salary field
        salaryTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) salaryTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    
}
