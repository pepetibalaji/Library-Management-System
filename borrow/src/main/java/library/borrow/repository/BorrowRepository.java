package library.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.borrow.entity.Borrow;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByMemberId(Long memberId);

}
