package ukma.groupproject.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

@Repository
public class HibernateSupplierDao extends AbstractHibernateDao<Supplier, Long> implements SupplierDao {

    private static final String HQL_SELECT_ALL_SUPPLIERS = "select s from Supplier s";
    private static final String HQL_SELECT_SUPPLIER_BY_NAME = "select s from Supplier s where s.name=:name";
    private static final String HQL_SELECT_SUPPLIER_SUPPLYING_ITEM = "select i.suppliedBy from Item i where i=:item";

    public HibernateSupplierDao() {
        super(Supplier.class);
    }

    @Override
    public List<Supplier> getAll() {
        return getSession().createQuery(HQL_SELECT_ALL_SUPPLIERS).list();
    }

    @Override
    public Supplier getByName(String name) {
        return (Supplier) getSession().createQuery(HQL_SELECT_SUPPLIER_BY_NAME).setParameter("name", name).uniqueResult();
    }

    @Override
    public List<Supplier> getSupplying(Item item) {
        return getSession().createQuery(HQL_SELECT_SUPPLIER_SUPPLYING_ITEM).setParameter("item", item).list();
    }
}
