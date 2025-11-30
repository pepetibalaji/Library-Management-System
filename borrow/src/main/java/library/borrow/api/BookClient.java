package library.borrow.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import library.borrow.config.FeignClientConfig;
import library.borrow.dto.BookQuantityResponse;
import library.borrow.dto.BookResponse;
import library.borrow.dto.EditBook;


@FeignClient(name="gateway-service",contextId = "bookClient",url="http://localhost:8081",configuration = FeignClientConfig.class)
public interface BookClient {

    @GetMapping("/books")
    BookResponse getBook(@RequestParam("id") Long id);

    @PutMapping("/books/{id}")
    EditBook editBook(@PathVariable Long id, @RequestBody EditBook entity);

    @PutMapping("/books/decrement")
        BookQuantityResponse decrementBookCount(@RequestParam Long id);
}
