package ukma.groupproject.shop.service;

import java.util.List;

import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.Employee;

public interface EmployeeService {

    Employee get(Long id);

    List<Employee> getAll();

    List<Employee> getByDepartment(Department department);

    void persist(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

}
