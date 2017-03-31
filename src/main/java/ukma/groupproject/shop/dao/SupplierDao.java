package ukma.groupproject.shop.dao;

import java.util.List;

import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Supplier;

public interface SupplierDao extends Dao<Supplier, Long> {

	List<Supplier> getAll();

	Supplier getByName(String name);

	List<Supplier> getSupplying(Item item);

}
