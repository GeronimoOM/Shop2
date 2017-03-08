package ukma.groupproject.shop.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ukma.groupproject.shop.dao.Dao;

public abstract class AbstractHibernateDao<E, K extends Serializable> implements Dao<E, K> {

    protected final Class<E> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public AbstractHibernateDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E get(K key) {
        return getSession().get(entityClass, key);
    }

    @Override
    public void persist(E entity) {
        getSession().persist(entity);
    }

    @Override
    public void update(E entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

}
