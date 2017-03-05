package ukma.groupproject.shop.dao.impl;

import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.DepartmentDao;
import ukma.groupproject.shop.dao.HibernateDao;
import ukma.groupproject.shop.model.Department;

@Repository
public class HibernateDepartmentDao extends HibernateDao<Department, Long> implements DepartmentDao {

    public HibernateDepartmentDao() {
        super(Department.class);
    }

    @Override
    public Department getWithEmployees(Long id) {
        return (Department) getSession().createQuery("select d from Department d join fetch d.employees").getSingleResult();
    }
}
