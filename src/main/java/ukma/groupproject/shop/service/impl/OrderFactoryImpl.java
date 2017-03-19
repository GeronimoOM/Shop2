package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.OrderFactory;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.Date;

@Service
public class OrderFactoryImpl implements OrderFactory{

    @Autowired
    private SupplierService supplierService;

    @Override
    public Order create(Employee employee, Supplier supplier, Item item, int amount) {
        if(!supplierService.supplies(supplier, item)) {
            throw new ShopBusinessException("Supplier doesn't supply this item");
        }

        Order order = new Order();
        order.setDate(new Date());
        order.setEmployee(employee);
        order.setSupplier(supplier);
        order.setItem(item);
        order.setAmount(amount);
        return order;
    }
}
