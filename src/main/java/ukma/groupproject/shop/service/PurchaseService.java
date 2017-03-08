package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Purchase;

public interface PurchaseService {

    Purchase get(Long id);

    void persist(Purchase purchase);

    void update(Purchase purchase);

    void delete(Purchase purchase);

}
