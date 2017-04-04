package ukma.groupproject.shop.model.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierView {

    private Long id;
    private StringProperty name;

    public SupplierView() {
        this.name = new SimpleStringProperty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

}
