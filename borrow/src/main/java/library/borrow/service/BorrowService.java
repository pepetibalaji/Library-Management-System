package library.borrow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import library.borrow.api.BookClient;
import library.borrow.api.MemberClient;
import library.borrow.dto.*;
import library.borrow.entity.Borrow;
import library.borrow.globalException.*;
import library.borrow.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowService {

        private final MemberClient memberClient;
        private final BookClient bookClient;
        private final BorrowRepository borrowRepository;

        public BorrowResponse borrowBook(BorrowRequest request) {
                MemberResponse member = memberClient.getMember();
                if (member.getStatus() == "expired") {
                        throw new UserDeactivated("User Deactivated");
                } else {
                        BookResponse book = bookClient.getBook(request.getBookId());
                        if (book.getQuantity() < 1) {
                                throw new OutOfStock("Book Out Of Stock");
                        } else {
                                bookClient.decrementBookCount(book.getId());

                                Borrow borrow = Borrow.builder()
                                                .memberId(member.getId())
                                                .bookId(book.getId())
                                                .borrowDate(LocalDate.now())
                                                .dueDate(LocalDate.now().plusDays(14))
                                                .status("BORROWED")
                                                .build();

                                borrowRepository.save(borrow);

                                return BorrowResponse.builder()
                                                .id(borrow.getId())
                                                .memberId(borrow.getMemberId())
                                                .bookId(borrow.getBookId())
                                                .borrowDate(borrow.getBorrowDate())
                                                .dueDate(borrow.getDueDate())
                                                .returnDate(borrow.getReturnDate())
                                                .status(borrow.getStatus())
                                                .build();
                        }
                }
        }

        public void returnBook(Long id) {
                Borrow borrowId = borrowRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Borrow id not Found"));
                if (borrowId.getReturnDate() != null) {
                        throw new BookAlreadyReturned("Book has already been returned");
                } else {
                        borrowId.setReturnDate(LocalDate.now());
                        borrowRepository.save(borrowId);
                }
        }

        public List<Borrow> getAllBorrowRecords() {
                List<Borrow> allBorrowRecords = borrowRepository.findAll();
                return allBorrowRecords;
        }

        public List<Borrow> getBorrowRecordsForSpecificMember(Long id) {
                List<Borrow> records = borrowRepository.findByMemberId(id);
                return records;
        }

}
