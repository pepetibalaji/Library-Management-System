package auth.service.globalException;

public class IncorrectUserNameOrPassword extends RuntimeException {
    public IncorrectUserNameOrPassword(String message) {
        super(message);
    }
}
