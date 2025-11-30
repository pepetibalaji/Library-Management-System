package library.borrow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowResult {
    private BorrowResponse borrowResponse;
}
