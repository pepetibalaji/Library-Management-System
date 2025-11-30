package library.borrow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookQuantityUpdate {
    private Long quantity;

}
