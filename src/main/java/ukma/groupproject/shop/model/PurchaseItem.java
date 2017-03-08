package ukma.groupproject.shop.model;

import ukma.groupproject.shop.model.persistence.PurchaseItemPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sh_purchases_items")
public class PurchaseItem implements Serializable {

    @EmbeddedId
    private PurchaseItemPK key = new PurchaseItemPK();

    private int amount;

    public PurchaseItem() {
    }

    public PurchaseItem(Purchase purchase, Item item, int amount) {
        key.setItem(item);
        key.setPurchase(purchase);
        this.amount = amount;
    }

    public Purchase getPurchase() {
        return key.getPurchase();
    }

    public void setPurchase(Purchase purchase) {
        key.setPurchase(purchase);
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

        PurchaseItem that = (PurchaseItem) o;

        return key != null ? key.equals(that.key) : that.key == null;

    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
