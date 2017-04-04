package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.view.ItemView;

@Component
public class ItemViewMapper extends AbstractMapper<Item, ItemView> {

    public ItemViewMapper() {
        super(Item.class, ItemView.class);
    }

    @Override
    public void mapTo(Item item, ItemView itemView) {
        itemView.setId(item.getId());
        itemView.setName(item.getName());
        itemView.setPrice(item.getPrice());
        itemView.setAmount(item.getAmount());
        itemView.setMinAmount(item.getMinAmount());
        itemView.setDepartment(item.getDepartment());
    }

    @Override
    public void mapFrom(ItemView itemView, Item item) {
        item.setId(itemView.getId());
        item.setName(itemView.getName());
        item.setPrice(itemView.getPrice());
        item.setAmount(itemView.getAmount());
        item.setMinAmount(itemView.getMinAmount());
        item.setDepartment(itemView.getDepartment());
    }
}
