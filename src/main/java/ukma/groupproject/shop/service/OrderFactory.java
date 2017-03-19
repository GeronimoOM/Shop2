package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.model.dto.ItemAmount;

import java.util.List;

public interface OrderFactory {

    Order create(Employee employee, Supplier supplier, Item item, int amount);

}
