package ukma.groupproject.shop.service;

import ukma.groupproject.shop.model.Department;

public interface DepamentService {

    Department get(Long id);

    Department getWithEmployees(Long id);

}
