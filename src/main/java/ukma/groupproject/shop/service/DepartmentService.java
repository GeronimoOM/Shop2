package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Department;

public interface DepartmentService {

    Department get(Long id);

    void persist(Department department);

    void update(Department department);

    void delete(Department department);

    Department getWithEmployees(Long id);

}
