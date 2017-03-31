package ukma.groupproject.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ukma.groupproject.shop.dao.EmployeeDao;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee get(Long id) {
        return employeeDao.get(id);
    }

    @Override
    public void persist(Employee employee) {
        employeeDao.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

	@Override
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}

    @Override
    public List<Employee> getByDepartment(Department department) {
        return employeeDao.getByDepartment(department);
    }
}
