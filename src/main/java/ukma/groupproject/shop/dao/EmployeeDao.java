package ukma.groupproject.shop.dao;

import java.util.List;

import ukma.groupproject.shop.model.Employee;

public interface EmployeeDao extends Dao<Employee, Long> {

    List<Employee> getAll();
}

