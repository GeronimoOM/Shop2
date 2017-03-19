package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface ItemService {

    Item get(Long id);

    void persist(Item item);

    void update(Item item);

    void delete(Item item);

    List<Item> getAll();

    List<Item> getByDepartment(Department department);

    List<Item> getSuppliedBy(Supplier supplier);

}
