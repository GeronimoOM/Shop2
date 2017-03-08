package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;

import java.util.List;

@Repository
public class HibernateOrderDao extends AbstractHibernateDao<Order, Long> implements OrderDao {

    private static final String HQL_SELECT_ORDERS_BY_EMPLOYEE = "select o from Order o where o.employee=:employee";
    private static final String HQL_SELECT_ORDER_WITH_ITEMS = "select o from Order o join fetch o.items where o.id=:id";

    public HibernateOrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getOrderedBy(Employee employee) {
        return getSession().createQuery(HQL_SELECT_ORDERS_BY_EMPLOYEE).setParameter("employee", employee).list();
    }

    @Override
    public Order getWithItems(Long id) {
        return (Order) getSession().createQuery(HQL_SELECT_ORDER_WITH_ITEMS).setParameter("id", id).uniqueResult();
    }
}
