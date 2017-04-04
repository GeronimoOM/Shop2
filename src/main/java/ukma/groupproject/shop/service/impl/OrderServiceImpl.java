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
import ukma.groupproject.shop.service.util.ShopBusinessException;

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
    public void delete(Order order) {
        if(orderDao.get(order.getId()).getSupply() == null) {
            orderDao.delete(order);
        } else {
            throw new ShopBusinessException("Order is already fulfilled");
        }

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
