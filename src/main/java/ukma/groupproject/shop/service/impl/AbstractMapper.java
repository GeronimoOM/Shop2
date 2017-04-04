package ukma.groupproject.shop.service.impl;

import ukma.groupproject.shop.service.Mapper;

public abstract class AbstractMapper<T, U> implements Mapper<T, U> {

    private Class<T> tClass;
    private Class<U> uClass;

    public AbstractMapper(Class<T> tClass, Class<U> uClass) {
        this.tClass = tClass;
        this.uClass = uClass;
    }

    @Override
    public U mapTo(T from) {
        U uObj = null;
        try {
            uObj = uClass.newInstance();
            mapTo(from, uObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return uObj;
    }

    @Override
    public T mapFrom(U from) {
        T tObj = null;
        try {
            tObj = tClass.newInstance();
            mapFrom(from, tObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tObj;
    }

}
