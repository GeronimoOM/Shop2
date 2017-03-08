package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Employee;

public interface EmployeeService {

    Employee get(Long id);

    void persist(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

}
