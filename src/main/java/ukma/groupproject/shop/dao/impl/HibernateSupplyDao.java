package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.SupplyDao;
import ukma.groupproject.shop.model.Supply;

import java.util.List;

@Repository
public class HibernateSupplyDao extends AbstractHibernateDao<Supply, Long> implements SupplyDao {

    private static final String HQL_SELECT_ALL_SUPPLIES = "select s from Supply s join fetch s.items order by s.date";

    public HibernateSupplyDao() {
        super(Supply.class);
    }

    @Override
    public List<Supply> getAll() {
        return getSession().createQuery(HQL_SELECT_ALL_SUPPLIES).list();
    }
}
