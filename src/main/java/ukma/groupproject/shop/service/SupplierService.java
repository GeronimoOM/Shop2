package ukma.groupproject.shop.service;

import java.util.List;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

public interface SupplierService {

    Supplier get(Long id);

    Supplier getByName(String name);
    
    List<Supplier> getAll();

    List<Supplier> getSupplying(Item item);

    boolean supplies(Supplier supplier, Item item);

    void persist(Supplier s);

    void delete(Supplier s);
}
