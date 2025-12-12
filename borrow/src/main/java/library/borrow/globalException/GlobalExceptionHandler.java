package library.borrow.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberDoesNotExist.class)
    public ResponseEntity<String> memberDoesNotExist(MemberDoesNotExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookDoesNotExist.class)
    public ResponseEntity<String> bookDoesNotExist(BookDoesNotExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDeactivated.class)
    public ResponseEntity<String> userDeactivated(UserDeactivated ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OutOfStock.class)
    public ResponseEntity<String> outOfStock(OutOfStock ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyReturned.class)
    public ResponseEntity<String> BookAlreadyReturned(BookAlreadyReturned ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
