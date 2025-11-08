package bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BookService")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Long quantity;
}
