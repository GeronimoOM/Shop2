package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Service;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.SupplierFactory;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.List;

@Service
public class SupplierFactoryImpl implements SupplierFactory {

    @Override
    public Supplier create(String name, List<Item> items) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        if(items.isEmpty()) {
            throw new ShopBusinessException("Supplier must supply at least 1 item");
        }
        supplier.getItems().addAll(items);
        return supplier;
    }

}
