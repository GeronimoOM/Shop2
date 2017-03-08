package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.service.OrderService;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public void persist(Order order) {
        orderDao.persist(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public Order getWithItems(Long id) {
        return orderDao.getWithItems(id);
    }

    @Override
    public List<Order> getOrderedBy(Employee employee) {
        return orderDao.getOrderedBy(employee);
    }

}
