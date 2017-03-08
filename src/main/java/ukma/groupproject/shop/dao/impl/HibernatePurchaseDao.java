package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.PurchaseDao;
import ukma.groupproject.shop.model.Purchase;

@Repository
public class HibernatePurchaseDao extends AbstractHibernateDao<Purchase, Long> implements PurchaseDao {

    public HibernatePurchaseDao() {
        super(Purchase.class);
    }

}
