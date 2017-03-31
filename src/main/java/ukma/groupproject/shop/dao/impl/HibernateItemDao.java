package ukma.groupproject.shop.dao.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.ItemDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

@Repository
public class HibernateItemDao extends AbstractHibernateDao<Item, Long> implements ItemDao {

    private static final String HQL_SELECT_ALL_ITEMS = "select i from Item i join fetch i.department order by i.name";
    private static final String HQL_SELECT_ITEMS_BY_DEPARTMENT = "select i from Item i join fetch i.department where i.department=:department";
    private static final String HQL_SELECT_ITEMS_SUPPLIED_BY = "select s.items from Supplier s where s=:supplier";

    public HibernateItemDao() {
        super(Item.class);
    }

    @Override
    public List<Item> getAll() {
        return getSession().createQuery(HQL_SELECT_ALL_ITEMS).list();
    }

    @Override
    public List<Item> getByDepartment(Department department) {
        return getSession().createQuery(HQL_SELECT_ITEMS_BY_DEPARTMENT).setParameter("department", department).list();
    }

    @Override
    public List<Item> getSuppliedBy(Supplier supplier) {
        return getSession().createQuery(HQL_SELECT_ITEMS_SUPPLIED_BY).setParameter("supplier", supplier).list();
    }
}
