package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;

import java.util.List;

public interface OrderService {

    Order get(Long id);

    void persist(Order order);

    void update(Order order);

    void delete(Order order);

    Order getWithItems(Long id);

    List<Order> getOrderedBy(Employee employee);

}
