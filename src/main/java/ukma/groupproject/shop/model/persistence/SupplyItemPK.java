package ukma.groupproject.shop.model.persistence;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supply;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class SupplyItemPK implements Serializable{

    @ManyToOne
    private Supply supply;

    @ManyToOne
    private Item item;

    public SupplyItemPK() {}

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
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

        SupplyItemPK that = (SupplyItemPK) o;

        if (supply != null ? !supply.equals(that.supply) : that.supply != null) return false;
        return item != null ? item.equals(that.item) : that.item == null;

    }

    @Override
    public int hashCode() {
        int result = supply != null ? supply.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
