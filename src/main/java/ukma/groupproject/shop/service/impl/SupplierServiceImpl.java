package ukma.groupproject.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.SupplierService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemService itemService;

    @Override
    public Supplier get(Long id) {
        return supplierDao.get(id);
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
    public List<Supplier> getSupplying(Item item) {
        return supplierDao.getSupplying(item);
    }

    @Override
	public void persist(Supplier s) {
        if(supplierDao.getByName(s.getName()) != null) {
            throw new ShopBusinessException("Supplier with specified name already exists");
        }
		supplierDao.persist(s);
	}

    @Override
    public void update(Supplier s) {
        Supplier byName = supplierDao.getByName(s.getName());
        if(byName != null && !byName.equals(s)) {
            throw new ShopBusinessException("Supplier with specified name already exists");
        }
        supplierDao.update(s);
    }

    @Override
	public void delete(Supplier s) {
        if(orderDao.existOrdersFor(s)) {
            throw new ShopBusinessException("Supplier already has active orders");
        }
        supplierDao.delete(s);
	}
}
