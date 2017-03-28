package ukma.groupproject.shop.view.util;

public class DefaultStringConverter<E> implements StringConverter<E> {
    @Override
    public String convert(E object) {
        return object.toString();
    }
}
