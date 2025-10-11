package auth.service.globalException;

public class userDoesNotExist extends RuntimeException {
    public userDoesNotExist(String message) {
        super(message);
    }
}
