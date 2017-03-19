package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Service;
import ukma.groupproject.shop.model.*;
import ukma.groupproject.shop.service.util.ShopBusinessException;
import ukma.groupproject.shop.service.SupplyFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplyFactoryImpl implements SupplyFactory {

    @Override
    public Supply create(List<Order> orders, Map<Item, Float> prices) {
        if(orders.isEmpty()) {
            throw new ShopBusinessException("Supply must complete at least 1 Order");
        }
        Supply supply = new Supply();
        supply.setDate(new Date());
        supply.setSupplier(orders.get(0).getSupplier());
        supply.setOrders(orders);
        Map<Item, Integer> itemAmounts = new HashMap<>();
        for(Order order: orders) {
            if(!supply.getSupplier().equals(order.getSupplier())) {
                throw new ShopBusinessException("Supply by Supplier can only complete orders created specifically for him");
            }
            Integer amount = itemAmounts.get(order.getItem());
            if(amount == null) {
                amount = 0;
            }
            amount += order.getAmount();
            itemAmounts.put(order.getItem(), amount);
        }

        for(Map.Entry<Item, Integer> entry: itemAmounts.entrySet()) {
            Float price = prices.get(entry.getKey());
            if(price == null) {
                price = entry.getKey().getPrice();
            }
            SupplyItem supplyItem = new SupplyItem(supply, entry.getKey(), entry.getValue(), price);
            supply.getItems().add(supplyItem);
        }
        return supply;
    }

}
