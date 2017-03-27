package ukma.groupproject.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Supplier;

@Repository
public class HibernateSupplierDao extends AbstractHibernateDao<Supplier, Long> implements SupplierDao {

    private static final String HQL_SELECT_ALL_SUPPLIERS = "select s from Supplier s";

    public HibernateSupplierDao() {
        super(Supplier.class);
    }

    @Override
    public List<Supplier> getAll() {
        return getSession().createQuery(HQL_SELECT_ALL_SUPPLIERS).list();
    }

	@Override
	public void persist(Supplier s) {
		getSession().persist(s);
	}

	@Override
	public void delete(Supplier s) {
		getSession().delete(s);
	}
}
