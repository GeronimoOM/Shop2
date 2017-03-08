package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.DepartmentDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department get(Long id) {
        return departmentDao.get(id);
    }

    @Override
    public void persist(Department department) {
        departmentDao.persist(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public Department getWithEmployees(Long id) {
        return departmentDao.getWithEmployees(id);
    }

}
