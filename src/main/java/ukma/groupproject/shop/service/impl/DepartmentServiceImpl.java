package ukma.groupproject.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.DepartmentDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.service.DepartmentService;
import ukma.groupproject.shop.service.EmployeeService;
import ukma.groupproject.shop.service.util.ShopBusinessException;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Department get(Long id) {
        return departmentDao.get(id);
    }

    @Override
    public void persist(Department department) {
        if(departmentDao.getByName(department.getName()) == null) {
            departmentDao.persist(department);
        } else {
            throw new ShopBusinessException("Department name is not unique");
        }
    }

    @Override
    public void update(Department department) {
        Department byName = departmentDao.getByName(department.getName());
        if(byName == null || byName.equals(department)) {
            departmentDao.update(department);
        } else {
            throw new ShopBusinessException("Department name is not unique");
        }
    }

    @Override
    public void delete(Department department) {
        if(!employeeService.getByDepartment(department).isEmpty()) {
            throw new ShopBusinessException("Cannot delete department with employees");
        }
        departmentDao.delete(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    @Override
    public Department getWithEmployees(Long id) {
        return departmentDao.getWithEmployees(id);
    }

}
