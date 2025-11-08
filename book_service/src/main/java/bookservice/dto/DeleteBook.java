package bookservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBook {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Long quantity;
}
