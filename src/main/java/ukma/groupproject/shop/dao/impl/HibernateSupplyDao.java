package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.SupplyDao;
import ukma.groupproject.shop.model.Supply;

@Repository
public class HibernateSupplyDao extends AbstractHibernateDao<Supply, Long> implements SupplyDao {

    public HibernateSupplyDao() {
        super(Supply.class);
    }

}
