package ukma.groupproject.shop.model.persistence;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Purchase;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PurchaseItemPK implements Serializable {

    @ManyToOne
    private Purchase purchase;

    @ManyToOne
    private Item item;

    public PurchaseItemPK() {}


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseItemPK that = (PurchaseItemPK) o;

        if (purchase != null ? !purchase.equals(that.purchase) : that.purchase != null) return false;
        return item != null ? item.equals(that.item) : that.item == null;

    }

    @Override
    public int hashCode() {
        int result = purchase != null ? purchase.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
