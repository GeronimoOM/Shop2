package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.ItemDao;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public void persist(Item item) {
        itemDao.persist(item);
    }

    @Override
    public void update(Item item) {
        itemDao.update(item);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }
}
