package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Supply;

import java.util.List;

public interface SupplyService {

    Supply get(Long id);

    List<Supply> getAll();

    void persist(Supply supply);

    void update(Supply supply);

    void delete(Supply supply);
}
