package bookservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bookservice.dto.AddNewBook;
import bookservice.dto.DecrementBookCount;
import bookservice.dto.DeleteBook;
import bookservice.dto.Editbook;
import bookservice.dto.RetrieveBook;
import bookservice.service.Bookservice_class;
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

    private final Bookservice_class bookservice_class;

    @PostMapping
    public AddNewBook addNewBook(@RequestBody AddNewBook entity) {
        bookservice_class.save(entity);
        return entity;
    }

    @GetMapping("/all")
    public List<RetrieveBook> getAllbooks() {
        return bookservice_class.getAllBooks();
    }

    @GetMapping
    public RetrieveBook getRequestedBooks(@RequestParam Long id) {
        return bookservice_class.getRequestedBook(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Editbook editbook(@PathVariable Long id, @RequestBody Editbook entity) {
        return bookservice_class.editbook(id, entity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DeleteBook deleteBook(@PathVariable Long id) {
        return bookservice_class.deleteBook(id);

    }
    @PutMapping("/decrement")
    public DecrementBookCount decrementBookCount(@RequestParam Long id) {
        return bookservice_class.decrementBookCount(id);
}

}
