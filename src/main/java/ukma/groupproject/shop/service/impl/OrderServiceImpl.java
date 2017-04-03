package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.OrderService;
import ukma.groupproject.shop.service.SupplierService;

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
        //TODO Unless fulfilled
        orderDao.delete(order);
    }

    @Override
    public List<Order> getOrdersBy(Employee employee) {
        return orderDao.getOrdersBy(employee);
    }

    @Override
    public List<Order> getOrdersFor(Supplier supplier) {
        return orderDao.getOrdersFor(supplier);
    }

    @Override
    public List<Order> getActiveOrdersFor(Supplier supplier) {
        return orderDao.getActiveOrdersFor(supplier);
    }

	@Override
	public List<Order> getAll() {
		return orderDao.getAll();
	}

}
