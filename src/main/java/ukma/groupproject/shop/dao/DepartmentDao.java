package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Department;

import java.util.List;

public interface DepartmentDao extends Dao<Department, Long> {

    List<Department> getAll();

    Department getWithEmployees(Long id);

}
