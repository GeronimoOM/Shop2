package ukma.groupproject.shop.service;

public interface Mapper<T, U> {

    U mapTo(T obj);

    void mapTo(T t, U u);

    T mapFrom(U obj);

    void mapFrom(U u, T t);

}
