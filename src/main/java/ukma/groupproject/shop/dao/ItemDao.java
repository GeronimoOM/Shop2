package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

import java.util.List;

public interface ItemDao extends Dao<Item, Long> {

    List<Item> getAll();

    Item getByName(String name);

    List<Item> getByDepartment(Department department);

    List<Item> getSuppliedBy(Supplier supplier);
}
