package bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BookService")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String author;

    @Column(nullable = true)
    private String isbn;

    @Column(nullable = true)
    private Long quantity;
}
