package bookservice.globalException;

public class BooksNotFound extends RuntimeException {

    public BooksNotFound(String message) {
        super(message);
    }

}
