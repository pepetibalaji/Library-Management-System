package library.borrow.globalException;

public class BookDoesNotExist extends RuntimeException {

    public BookDoesNotExist(String message) {
        super(message);
    }
}
