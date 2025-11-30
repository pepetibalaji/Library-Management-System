package bookservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bookservice.dto.AddNewBook;
import bookservice.dto.DecrementBookCount;
import bookservice.dto.DeleteBook;
import bookservice.dto.Editbook;
import bookservice.dto.RetrieveBook;
import bookservice.entity.BookService;
import bookservice.globalException.BooksNotFound;
import bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Bookservice_class {

        private final BookRepository bookRepository;

        @Transactional(rollbackFor = Exception.class)
        public AddNewBook save(AddNewBook entity) {
                BookService bookService = BookService.builder()
                                .title(entity.getTitle())
                                .author(entity.getAuthor())
                                .isbn(entity.getIsbn())
                                .quantity(entity.getQuantity())
                                .build();
                bookRepository.save(bookService);
                return entity;
        }

        public List<RetrieveBook> getAllBooks() {
                List<BookService> allBooks = bookRepository.findAll();
                return allBooks.stream()
                                .map(u -> new RetrieveBook(
                                                u.getId(),
                                                u.getTitle(),
                                                u.getAuthor(),
                                                u.getIsbn(),
                                                u.getQuantity()))
                                .collect(Collectors.toList());
        }

        public RetrieveBook getRequestedBook(Long id) {
                BookService getRequestedBook = bookRepository.findById(id)
                                .orElseThrow(() -> new BooksNotFound("Book not found with id: " + id));
                return RetrieveBook.builder()
                                .id(getRequestedBook.getId())
                                .title(getRequestedBook.getTitle())
                                .author(getRequestedBook.getAuthor())
                                .isbn(getRequestedBook.getIsbn())
                                .quantity(getRequestedBook.getQuantity())
                                .build();
        }

        @Transactional(rollbackFor = Exception.class)
        public Editbook editbook(Long id, Editbook entity) {
                BookService book = bookRepository.findById(id)
                                .orElseThrow(() -> new BooksNotFound("Book not found with id: " + id));
                book.setTitle(entity.getTitle());
                book.setAuthor(entity.getAuthor());
                book.setIsbn(entity.getIsbn());
                book.setQuantity(entity.getQuantity());
                bookRepository.save(book);
                return Editbook.builder()
                                .title(book.getTitle())
                                .author(book.getAuthor())
                                .isbn(book.getIsbn())
                                .quantity(book.getQuantity())
                                .build();
        } 
        @Transactional(rollbackFor = Exception.class)
        public DecrementBookCount decrementBookCount(Long id) {
        BookService book = bookRepository.findById(id)
                .orElseThrow(() -> new BooksNotFound("Book not found with id: " + id));

        if (book.getQuantity() < 1) {
        throw new RuntimeException("Not enough books in stock");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        return DecrementBookCount.builder()
                .quantity(book.getQuantity())
                .build();
        }


        @Transactional(rollbackFor = Exception.class)
        public DeleteBook deleteBook(Long id) {
                BookService book = bookRepository.findById(id)
                                .orElseThrow(() -> new BooksNotFound("Book not found with id: " + id));
                bookRepository.deleteById(id);
                return DeleteBook.builder()
                                .id(book.getId())
                                .title(book.getTitle())
                                .author(book.getAuthor())
                                .isbn(book.getIsbn())
                                .quantity(book.getQuantity())
                                .build();
        }

}
