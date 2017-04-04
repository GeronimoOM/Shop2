package ukma.groupproject.shop.model.view;

import javafx.beans.property.*;
import ukma.groupproject.shop.model.Item;

public class ItemAmountPrice {

    private ObjectProperty<Item> item;

    private IntegerProperty amount;

    private FloatProperty price;

    public ItemAmountPrice(Item item, int amount, float price) {
        this.item = new SimpleObjectProperty<>(item);
        this.amount = new SimpleIntegerProperty(amount);
        this.price = new SimpleFloatProperty(price);
    }

    public Item getItem() {
        return item.get();
    }

    public void setItem(Item item) {
        this.item.set(item);
    }

    public ObjectProperty<Item> itemProperty() {
        return item;
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public float getPrice() {
        return price.get();
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public FloatProperty priceProperty() {
        return price;
    }
}
