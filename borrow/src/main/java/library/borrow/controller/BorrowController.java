package library.borrow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.borrow.dto.BorrowRequest;
import library.borrow.dto.BorrowResponse;
import library.borrow.dto.BorrowResult;
import library.borrow.service.BorrowService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {
    
    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowResponse> borrowBook(@RequestBody BorrowRequest request) {
        BorrowResponse response = borrowService.borrowBook(request);
        return ResponseEntity.ok(response);
    }
}
