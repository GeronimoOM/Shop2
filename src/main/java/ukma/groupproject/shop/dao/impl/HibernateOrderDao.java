package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

@Repository
public class HibernateOrderDao extends AbstractHibernateDao<Order, Long> implements OrderDao {

    private static final String HQL_SELECT_ORDERS_BY_EMPLOYEE = "select o from Order o where o.employee=:employee";
    private static final String HQL_SELECT_ORDERS_BY_SUPPLIER = "select o from Order o where o.supplier=:supplier";
    private static final String HQL_SELECT_ACTIVE_ORDERS_BY_SUPPLIER = "select o from Order o where o.supplier=:supplier AND o.supply IS NULL";

    public HibernateOrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getOrdersBy(Employee employee) {
        return getSession().createQuery(HQL_SELECT_ORDERS_BY_EMPLOYEE).setParameter("employee", employee).list();
    }

    @Override
    public List<Order> getOrdersFor(Supplier supplier) {
        return getSession().createQuery(HQL_SELECT_ORDERS_BY_SUPPLIER).setParameter("supplier", supplier).list();
    }

    @Override
    public List<Order> getActiveOrdersFor(Supplier supplier) {
        return getSession().createQuery(HQL_SELECT_ACTIVE_ORDERS_BY_SUPPLIER).setParameter("supplier", supplier).list();
    }
}
