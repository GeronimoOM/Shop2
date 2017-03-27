package ukma.groupproject.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.PurchaseDao;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.service.PurchaseService;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public Purchase get(Long id) {
        return purchaseDao.get(id);
    }

    @Override
    public void persist(Purchase purchase) {
        purchaseDao.persist(purchase);
    }

    @Override
    public void update(Purchase purchase) {
        purchaseDao.update(purchase);
    }

    @Override
    public void delete(Purchase purchase) {
        purchaseDao.delete(purchase);
    }

	@Override
	public List<Purchase> getAll() {
		return purchaseDao.getAll();
	}
}
