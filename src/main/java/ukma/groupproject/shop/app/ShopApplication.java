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

    private ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private Stage mainStage;

    @Autowired
    private SpringFxmlLoader fxmlLoader;

    @Override
    public void start(Stage mainStage) {
        this.mainStage = mainStage;
        Controller controller = fxmlLoader.load("Main");
        Scene scene = new Scene((Parent) controller.getView());
        scene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);
        mainStage.setTitle("Shop");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    @Override
    protected ApplicationContext getApplicationContext() {
        return context;
    }

    public static void main(String[] args) {
        launch(ShopApplication.class, args);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
