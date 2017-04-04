package ukma.groupproject.shop.model.view;

import javafx.beans.property.*;
import ukma.groupproject.shop.model.Department;

public class ItemView {

    private Long id;
    private StringProperty name;
    private FloatProperty price;
    private IntegerProperty amount;
    private IntegerProperty minAmount;
    private ObjectProperty<Department> department;

    public ItemView() {
        name = new SimpleStringProperty();
        price = new SimpleFloatProperty();
        amount = new SimpleIntegerProperty();
        minAmount = new SimpleIntegerProperty();
        department = new SimpleObjectProperty<>();
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

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getMinAmount() {
        return minAmount.get();
    }

    public IntegerProperty minAmountProperty() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount.set(minAmount);
    }

    public Department getDepartment() {
        return department.get();
    }

    public ObjectProperty<Department> departmentProperty() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department.set(department);
    }
}
