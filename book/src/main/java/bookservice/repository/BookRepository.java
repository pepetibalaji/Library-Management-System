package bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
