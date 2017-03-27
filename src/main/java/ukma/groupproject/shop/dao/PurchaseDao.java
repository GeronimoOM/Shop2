package ukma.groupproject.shop.dao;

import java.util.List;

import ukma.groupproject.shop.model.Purchase;

public interface PurchaseDao extends Dao<Purchase, Long> {
	List<Purchase> getAll();
}
