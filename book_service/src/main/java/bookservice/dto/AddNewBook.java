package bookservice.dto;

import lombok.Data;

@Data
public class AddNewBook {
    private String title;
    private String author;
    private String isbn;
    private Long quantity;

}
