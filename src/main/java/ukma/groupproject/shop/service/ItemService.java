package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface ItemService {

    Item get(Long id);

    void persist(Item item);

    void update(Item item);

    void delete(Item item);

    List<Item> getSuppliedBy(Supplier supplier);

}
