package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Item;

public interface ItemService {

    Item get(Long id);

    void persist(Item item);

    void update(Item item);

    void delete(Item item);

}
