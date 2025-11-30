package library.borrow.globalException;

public class UserDeactivated extends RuntimeException {

    public UserDeactivated(String message) {
        super(message);
    }
}
