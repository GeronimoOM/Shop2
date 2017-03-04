package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public Supplier find(Long supplierId) {
        return supplierDao.find(supplierId);
    }

}
