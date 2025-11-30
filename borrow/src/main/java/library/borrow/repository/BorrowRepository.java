package library.borrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import library.borrow.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}
