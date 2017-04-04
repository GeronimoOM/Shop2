package ukma.groupproject.shop.context.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.context.View;
import ukma.groupproject.shop.context.ViewResolver;
import ukma.groupproject.shop.controller.Controller;

import java.io.IOException;

@Component
public class SpringFxmlLoaderImpl implements SpringFxmlLoader {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ViewResolver viewResolver;

    @Override
    public Controller load(View view) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(view.getUrl());
            loader.setControllerFactory(controllerClass -> applicationContext.getBean(controllerClass));
            Node viewNode = loader.load();
            Controller controller = loader.getController();
            if(controller != null) {
                controller.setView(viewNode);
            }
            return controller;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load view file: " + view.getUrl(), e);
        }
    }

    @Override
    public Controller load(String viewName) {
       return load(viewResolver.resolve(viewName));
    }



}
