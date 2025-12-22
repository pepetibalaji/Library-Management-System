package bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveBook {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Long quantity;

}
