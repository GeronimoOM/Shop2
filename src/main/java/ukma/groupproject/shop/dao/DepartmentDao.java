package ukma.groupproject.shop.dao;

import ukma.groupproject.shop.model.Department;

public interface DepartmentDao extends Dao<Department, Long> {

    Department getWithEmployees(Long id);

}
