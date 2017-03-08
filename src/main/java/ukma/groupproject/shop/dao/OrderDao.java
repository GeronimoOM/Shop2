package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;

import java.util.List;

public interface OrderDao extends Dao<Order, Long> {

    List<Order> getOrderedBy(Employee employee);

    Order getWithItems(Long id);
}
