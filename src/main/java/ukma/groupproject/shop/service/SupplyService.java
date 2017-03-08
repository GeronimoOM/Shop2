package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Supply;

public interface SupplyService {

    Supply get(Long id);

    void persist(Supply supply);

    void update(Supply supply);

    void delete(Supply supply);
}
