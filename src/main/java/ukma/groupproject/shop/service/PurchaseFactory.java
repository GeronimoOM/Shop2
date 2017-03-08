package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.dto.ItemAmount;

import java.util.List;

public interface PurchaseFactory {

    Purchase create(Employee employee, List<ItemAmount> items);
}
