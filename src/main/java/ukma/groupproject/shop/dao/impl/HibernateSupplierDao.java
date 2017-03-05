package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.HibernateDao;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Supplier;

@Repository
public class HibernateSupplierDao extends HibernateDao<Supplier, Long> implements SupplierDao {

    public HibernateSupplierDao() {
        super(Supplier.class);
    }
}
