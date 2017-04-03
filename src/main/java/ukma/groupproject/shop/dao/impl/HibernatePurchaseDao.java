package ukma.groupproject.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.PurchaseDao;
import ukma.groupproject.shop.model.Purchase;

@Repository
public class HibernatePurchaseDao extends AbstractHibernateDao<Purchase, Long> implements PurchaseDao {

    private static final String HQL_SELECT_ALL_PURCHASES = "select p from Purchase p";
    private static final String HQL_SELECT_ALL_PURCHASES_WITH_ITEMS = "select p from Purchase p join fetch p.items";

    public HibernatePurchaseDao() {
        super(Purchase.class);
    }

	@Override
	public List<Purchase> getAll() {
		return getSession().createQuery(HQL_SELECT_ALL_PURCHASES).list();
	}

    @Override
    public List<Purchase> getAllWithItems() {
        return getSession().createQuery(HQL_SELECT_ALL_PURCHASES_WITH_ITEMS).list();
    }

}
