package library.borrow.globalException;

public class BookAlreadyReturned extends RuntimeException {

    public BookAlreadyReturned(String message) {
        super(message);
    }

}
