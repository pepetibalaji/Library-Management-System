package library.borrow.globalException;

public class MemberDoesNotExist extends RuntimeException {

    public MemberDoesNotExist(String message) {
        super(message);
    }

}
