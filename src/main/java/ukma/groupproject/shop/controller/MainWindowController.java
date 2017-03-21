package ukma.groupproject.shop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

@Component
public class MainWindowController extends Controller {

    @FXML private TabbedPaneUI mainTabPane;
    
    @FXML private MenuItem itemsMenuItem;
    
    @FXML private Tab itemsTab;
    

    @Override
    public void initialize() {
    	itemsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                itemsTab.setDisable(false);
            }
        });
    }
    
    
}
