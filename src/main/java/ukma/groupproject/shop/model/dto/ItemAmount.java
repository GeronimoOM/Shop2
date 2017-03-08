package ukma.groupproject.shop.model.dto;

import ukma.groupproject.shop.model.Item;

public class ItemAmount {

    private Item item;

    private int amount;

    public ItemAmount(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemAmount(Item item) {
        this(item, 1);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
