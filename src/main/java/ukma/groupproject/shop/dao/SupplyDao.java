package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Supply;

import java.util.List;

public interface SupplyDao extends Dao<Supply, Long> {

    List<Supply> getAll();

}
