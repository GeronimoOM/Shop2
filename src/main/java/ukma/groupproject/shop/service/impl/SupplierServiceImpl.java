package ukma.groupproject.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private ItemService itemService;

    @Override
    public Supplier get(Long id) {
        return supplierDao.get(id);
    }

    @Override
    public Supplier getByName(String name) {
        return supplierDao.getByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean supplies(Supplier supplier, Item item) {
        return itemService.getSuppliedBy(supplier).contains(item);
    }

	@Override
	public List<Supplier> getAll() {
        return supplierDao.getAll();
	}

	@Override
	public void persist(Supplier s) {
		supplierDao.persist(s);
	}

	@Override
	public void delete(Supplier s) {
		supplierDao.delete(s);
	}
}
