package bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookservice.entity.BookService;

public interface BookRepository extends JpaRepository<BookService, Long> {

}
