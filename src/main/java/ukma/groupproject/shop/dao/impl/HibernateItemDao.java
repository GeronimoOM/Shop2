package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.ItemDao;
import ukma.groupproject.shop.model.Item;

@Repository
public class HibernateItemDao extends AbstractHibernateDao<Item, Long> implements ItemDao {

    public HibernateItemDao() {
        super(Item.class);
    }

}
