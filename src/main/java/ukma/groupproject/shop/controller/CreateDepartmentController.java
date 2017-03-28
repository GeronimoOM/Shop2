package ukma.groupproject.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.service.DepartmentService;

@Component
@Scope("prototype")
public class CreateDepartmentController extends Controller {

	@FXML Button createButton;
	
	@FXML TextField nameTextField;

    @Autowired private DepartmentService departmentService;

    @Override
    public void initialize() 
    {
    	createButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			createButton.getParent().getParent().setDisable(true);
            	boolean alreadyContains = false;
            	for (Department d : departmentService.getAll())
            		if (d.getName().equals(nameTextField.getText())) alreadyContains = true;
            	if (nameTextField.getText().equals("") || alreadyContains)
            	{
            		Alert alert = new Alert(AlertType.ERROR, alreadyContains ? "Such department already exists!" : "Name cannot be empty!", ButtonType.OK);
            		alert.initModality(Modality.APPLICATION_MODAL);
            		alert.showAndWait();
            	}
            	else
            	{
            		Department d = new Department();
            		d.setName(nameTextField.getText());
            		departmentService.persist(d);
            		 
            		Stage stage = (Stage) createButton.getScene().getWindow();
            		stage.close();
            	}
            	createButton.getParent().getParent().setDisable(false);
            }
        });
    }
    
}
