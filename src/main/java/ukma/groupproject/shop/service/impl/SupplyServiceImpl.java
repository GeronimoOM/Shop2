package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.SupplyDao;
import ukma.groupproject.shop.model.Supply;
import ukma.groupproject.shop.service.SupplyService;

import java.util.List;

@Service
@Transactional
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyDao supplyDao;

    @Override
    public Supply get(Long id) {
        return supplyDao.get(id);
    }

    @Override
    public List<Supply> getAll() {
        return supplyDao.getAll();
    }

    @Override
    public void persist(Supply supply) {
        supplyDao.persist(supply);
    }

    @Override
    public void update(Supply supply) {
        supplyDao.update(supply);
    }

    @Override
    public void delete(Supply supply) {
        supplyDao.delete(supply);
    }
}
