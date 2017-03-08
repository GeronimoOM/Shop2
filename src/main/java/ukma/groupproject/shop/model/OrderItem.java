package ukma.groupproject.shop.model;

import ukma.groupproject.shop.model.persistence.OrderItemPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sh_orders_items")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK key = new OrderItemPK();

    private int amount;

    public OrderItem() {}

    public OrderItem(Order order, Item item, int amount) {
        key.setOrder(order);
        key.setItem(item);
        this.amount = amount;
    }

    public Order getOrder() {
        return key.getOrder();
    }

    public void setOrder(Order order) {
        key.setOrder(order);
    }

    public Item getItem() {
        return key.getItem();
    }

    public void setItem(Item item) {
        key.setItem(item);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return key != null ? key.equals(orderItem.key) : orderItem.key == null;

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
