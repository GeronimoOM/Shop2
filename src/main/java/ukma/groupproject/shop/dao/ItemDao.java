package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface ItemDao extends Dao<Item, Long> {

    List<Item> getSuppliedBy(Supplier supplier);
}
