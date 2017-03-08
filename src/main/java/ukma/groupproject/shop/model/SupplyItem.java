package ukma.groupproject.shop.model;

import ukma.groupproject.shop.model.persistence.SupplyItemPK;

import javax.persistence.*;

@Entity
@Table(name = "sh_supplies_items")
public class SupplyItem {

    @EmbeddedId
    private SupplyItemPK key = new SupplyItemPK();

    private int amount;

    private float price;

    public SupplyItem() {}

    public SupplyItem(Supply supply, Item item, int amount, float price) {
        key.setSupply(supply);
        key.setItem(item);
        this.amount = amount;
        this.price = price;
    }

    public Supply getSupply() {
        return key.getSupply();
    }

    public void setSupply(Supply supply) {
        key.setSupply(supply);
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplyItem that = (SupplyItem) o;

        return key != null ? key.equals(that.key) : that.key == null;

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

}
