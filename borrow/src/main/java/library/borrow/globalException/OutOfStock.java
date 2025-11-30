package library.borrow.globalException;

public class OutOfStock extends RuntimeException {

    public OutOfStock(String message) {
        super(message);
    }
}
