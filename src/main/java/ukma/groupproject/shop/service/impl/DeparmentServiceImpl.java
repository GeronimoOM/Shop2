package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.DepartmentDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.service.DepamentService;

@Service
@Transactional
public class DeparmentServiceImpl implements DepamentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department get(Long id) {
        return departmentDao.get(id);
    }

    @Override
    public Department getWithEmployees(Long id) {
        return departmentDao.getWithEmployees(id);
    }

}
