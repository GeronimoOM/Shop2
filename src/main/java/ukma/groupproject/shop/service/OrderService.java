package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface OrderService {

    Order get(Long id);

    void persist(Order order);

    void update(Order order);

    void delete(Order order);

    List<Order> getOrdersBy(Employee employee);

    List<Order> getOrdersFor(Supplier supplier);

    List<Order> getActiveOrdersFor(Supplier supplier);

}
