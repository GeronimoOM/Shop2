package ukma.groupproject.shop.dao;

import java.io.Serializable;

public interface Dao<E, K extends Serializable> {

    E get(K key);

    void persist(E entity);

    void update(E entity);

    void delete(E entity);

}
