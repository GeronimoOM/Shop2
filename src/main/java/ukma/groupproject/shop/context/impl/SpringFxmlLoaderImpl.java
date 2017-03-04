package ukma.groupproject.shop.context.impl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.controller.Controller;

import java.io.IOException;

@Component
public class SpringFxmlLoaderImpl implements SpringFxmlLoader {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Controller load(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SpringFxmlLoaderImpl.class.getResource(path));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> controllerClass) {
                    return applicationContext.getBean(controllerClass);
                }
            });
            Node view = loader.load();
            Controller controller = loader.getController();
            controller.setView(view);
            controller.initialize();
            return controller;
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException("Failed to load FXML: " + path, e);
        }
    }


}
