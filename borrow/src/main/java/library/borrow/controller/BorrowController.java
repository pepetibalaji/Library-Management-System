package library.borrow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.borrow.dto.BorrowRequest;
import library.borrow.dto.BorrowResponse;
import library.borrow.entity.Borrow;
import library.borrow.service.BorrowService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/all")
    public List<Borrow> getAllBorrowRecords() {
        return borrowService.getAllBorrowRecords();
    }

    @GetMapping("/member/{memberId}")
    public List<Borrow> getBorrowRecordsForSpecificMember(@PathVariable Long memberId) {
        List<Borrow> records = borrowService.getBorrowRecordsForSpecificMember(memberId);
        return records;
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<String> returnRequest(@PathVariable Long id) {
        borrowService.returnBook(id);
        return ResponseEntity.ok("Returned book Successfully");
    }

}
