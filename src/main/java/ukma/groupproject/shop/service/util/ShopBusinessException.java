package ukma.groupproject.shop.service.util;

public class ShopBusinessException extends RuntimeException {

    public ShopBusinessException() {
    }

    public ShopBusinessException(String message) {
        super(message);
    }

    public ShopBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopBusinessException(Throwable cause) {
        super(cause);
    }
}
