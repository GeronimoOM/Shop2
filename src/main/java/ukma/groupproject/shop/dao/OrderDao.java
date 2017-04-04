package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface OrderDao extends Dao<Order, Long> {

    List<Order> getOrdersBy(Employee employee);

    List<Order> getOrdersFor(Supplier supplier);

    List<Order> getActiveOrdersFor(Supplier supplier);

    List<Order> getAll();

    boolean existOrdersFor(Supplier supplier);

    boolean existOrdersIncluding(Item item);
}
