package ukma.groupproject.shop.model.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DepartmentView {

    private Long id;

    private StringProperty name;

    public DepartmentView() {
        name = new SimpleStringProperty();
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

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
