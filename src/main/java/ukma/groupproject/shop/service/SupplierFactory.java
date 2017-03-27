package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface SupplierFactory {

    Supplier create(String name, List<Item> items);

}
