package ukma.groupproject.shop.app;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ukma.groupproject.shop.config.AppConfig;
import ukma.groupproject.shop.context.SpringFxmlLoader;
import ukma.groupproject.shop.controller.Controller;

public class ShopApplication extends SpringJavaFxApplication {

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    protected ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Override
    public void start(Stage primaryStage) {
        Controller controller = fxmlLoader.load("/views/MainWindow.fxml");
        Scene scene = new Scene((Parent) controller.getView());
        scene.getStylesheets().add("css/style.css");
        primaryStage.setTitle("Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    protected ApplicationContext getApplicationContext() {
        return context;
    }

    public static void main(String[] args) {
        launch(ShopApplication.class, args);
    }
}
