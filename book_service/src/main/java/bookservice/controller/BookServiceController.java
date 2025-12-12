package bookservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bookservice.dto.AddNewBook;
import bookservice.dto.DecrementBookCount;
import bookservice.dto.DeleteBook;
import bookservice.dto.EditBook;
import bookservice.dto.RetrieveBook;
import bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookServiceController {

    private final BookService bookService;

    @PostMapping
    public AddNewBook addNewBook(@RequestBody AddNewBook entity) {
        bookService.save(entity);
        return entity;
    }

    @GetMapping("/all")
    public List<RetrieveBook> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping
    public RetrieveBook getRequestedBooks(@RequestParam Long id) {
        return bookService.getRequestedBook(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EditBook editbook(@PathVariable Long id, @RequestBody EditBook entity) {
        return bookService.editbook(id, entity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DeleteBook deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);

    }

    @PutMapping("/decrement")
    public DecrementBookCount decrementBookCount(@RequestParam Long id) {
        return bookService.decrementBookCount(id);
    }

}
