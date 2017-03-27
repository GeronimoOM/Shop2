package ukma.groupproject.shop.dao;

import java.util.List;

import ukma.groupproject.shop.model.Supplier;

public interface SupplierDao extends Dao<Supplier, Long> {

	List<Supplier> getAll();
	
	void persist(Supplier s);
	void delete(Supplier s);
}
