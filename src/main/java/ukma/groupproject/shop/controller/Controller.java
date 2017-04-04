package ukma.groupproject.shop.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ukma.groupproject.shop.app.SpringJavaFxApplication;

public abstract class Controller {

    protected Node view;

    public abstract void initialize();


    protected Stage createModal(String title, Controller controller) {
        Scene modalScene = new Scene((Parent) controller.getView());
        modalScene.getStylesheets().add(SpringJavaFxApplication.STYLESHEETS);

        Stage modalStage = new Stage();
        modalStage.setTitle(title);
        modalStage.setScene(modalScene);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(getView().getScene().getWindow());
        return modalStage;
    }

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

}
