package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Department;

import java.util.List;

public interface DepartmentService {

    Department get(Long id);

    void persist(Department department);

    void update(Department department);

    void delete(Department department);

    List<Department> getAll();

    Department getWithEmployees(Long id);

}
