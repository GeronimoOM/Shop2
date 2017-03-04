package ukma.groupproject.shop.controller;

import javafx.scene.Node;

public abstract class Controller {

    private Node view;

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

    public void initialize() {};
}
