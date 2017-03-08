package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.DepartmentDao;
import ukma.groupproject.shop.model.Department;

@Repository
public class HibernateDepartmentDao extends AbstractHibernateDao<Department, Long> implements DepartmentDao {

    private static final String HQL_SELECT_DEPARTMENT_WITH_EMPLOYEES = "select d from Department d join fetch d.employees";

    public HibernateDepartmentDao() {
        super(Department.class);
    }

    @Override
    public Department getWithEmployees(Long id) {
        return (Department) getSession().createQuery(HQL_SELECT_DEPARTMENT_WITH_EMPLOYEES).uniqueResult();
    }
}
