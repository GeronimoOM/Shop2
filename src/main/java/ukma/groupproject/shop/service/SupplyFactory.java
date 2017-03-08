package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Supply;

import java.util.List;
import java.util.Map;

public interface SupplyFactory {

    Supply create(List<Order> orders, Map<Item, Float> prices);

}
