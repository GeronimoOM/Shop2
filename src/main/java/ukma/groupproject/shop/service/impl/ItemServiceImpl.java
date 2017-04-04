package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.ItemDao;
import ukma.groupproject.shop.dao.OrderDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.service.ItemService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    @Autowired private ItemDao itemDao;
    @Autowired private OrderDao orderDao;

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public void persist(Item item) {
        validatePriceAndMinAmount(item);
        if(itemDao.getByName(item.getName()) == null) {
            itemDao.persist(item);
        } else {
            throw new ShopBusinessException("Item name is not unique");
        }
    }

    @Override
    public void update(Item item) {
        validatePriceAndMinAmount(item);
        Item byName = itemDao.getByName(item.getName());
        if(byName == null || byName.equals(item)) {
            itemDao.update(item);
        } else {
            throw new ShopBusinessException("Item name is not unique");
        }
    }

    @Override
    public void delete(Item item) {
        if(!orderDao.existOrdersIncluding(item)) {
            itemDao.delete(item);
        } else {
            throw new ShopBusinessException("Item is already in use");
        }
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public List<Item> getByDepartment(Department department) {
        return itemDao.getByDepartment(department);
    }

    @Override
    public List<Item> getSuppliedBy(Supplier supplier) {
        return itemDao.getSuppliedBy(supplier);
    }

    private void validatePriceAndMinAmount(Item item) {
        if(item.getPrice() <= 0) {
            throw new ShopBusinessException("Price must be a positive number");
        }
        if(item.getMinAmount() < 0) {
            throw new ShopBusinessException("Minimum amount must be a positive number");
        }
    }
}
