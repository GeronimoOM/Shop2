package ukma.groupproject.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.EmployeeDao;
import ukma.groupproject.shop.model.Employee;

@Repository
public class HibernateEmployeeDao extends AbstractHibernateDao<Employee, Long> implements EmployeeDao {

    private static final String HQL_SELECT_ALL_EMPLOYEES = "select e from Employee e join fetch e.department";

    public HibernateEmployeeDao() {
        super(Employee.class);
    }

	@Override
	public List<Employee> getAll() {
        return getSession().createQuery(HQL_SELECT_ALL_EMPLOYEES).list();
	}
}
