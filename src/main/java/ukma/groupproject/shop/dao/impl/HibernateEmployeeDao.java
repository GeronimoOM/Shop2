package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.EmployeeDao;
import ukma.groupproject.shop.dao.HibernateDao;
import ukma.groupproject.shop.model.Employee;

@Repository
public class HibernateEmployeeDao extends HibernateDao<Employee, Long> implements EmployeeDao {

    public HibernateEmployeeDao() {
        super(Employee.class);
    }

}
