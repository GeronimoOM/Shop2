package ukma.groupproject.shop.dao;

import java.util.List;

import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;

public interface EmployeeDao extends Dao<Employee, Long> {

    List<Employee> getAll();

    List<Employee> getByDepartment(Department department);
}

