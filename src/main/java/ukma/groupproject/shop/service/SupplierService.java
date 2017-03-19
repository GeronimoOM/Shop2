package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

public interface SupplierService {

    Supplier get(Long id);

    boolean supplies(Supplier supplier, Item item);

}
