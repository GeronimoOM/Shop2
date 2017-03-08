package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Service;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.PurchaseItem;
import ukma.groupproject.shop.model.dto.ItemAmount;
import ukma.groupproject.shop.service.PurchaseFactory;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseFactoryImpl implements PurchaseFactory {

    @Override
    public Purchase create(Employee employee, List<ItemAmount> items) {
        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setEmployee(employee);
        for(ItemAmount itemAmount: items) {
            PurchaseItem purchaseItem = new PurchaseItem(purchase, itemAmount.getItem(), itemAmount.getAmount());
            if(purchase.getItems().contains(purchaseItem)) {
                throw new ShopBusinessException("Multiple ItemAmount objects for same Item");
            }
            purchase.getItems().add(purchaseItem);
        }
        return purchase;
    }

}
