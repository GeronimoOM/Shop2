package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Service;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.model.dto.ItemAmount;
import ukma.groupproject.shop.service.OrderFactory;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.Date;
import java.util.List;

@Service
public class OrderFactoryImpl implements OrderFactory{

    @Override
    public Order create(Employee employee, Supplier supplier, List<ItemAmount> items) {
        Order order = new Order();
        order.setDate(new Date());
        order.setEmployee(employee);
        order.setSupplier(supplier);
        for(ItemAmount itemAmount: items) {
            OrderItem orderItem = new OrderItem(order, itemAmount.getItem(), itemAmount.getAmount());
            if(order.getItems().contains(orderItem)) {
                throw new ShopBusinessException("Multiple ItemAmount objects for same Item");
            }
            order.getItems().add(orderItem);
        }
        return order;
    }

}
