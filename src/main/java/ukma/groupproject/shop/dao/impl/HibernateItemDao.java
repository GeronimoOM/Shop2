package ukma.groupproject.shop.dao.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.ItemDao;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

@Repository
public class HibernateItemDao extends AbstractHibernateDao<Item, Long> implements ItemDao {

    public HibernateItemDao() {
        super(Item.class);
    }

    @Override
    public List<Item> getSuppliedBy(Supplier supplier) {
        return getSession().createQuery("select s.items from Supplier s where s.id=:supplier_id").setParameter("supplier_id", supplier.getId()).list();
    }
}
