package bt.book.repository;

import bt.book.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    BorrowRecord findByBorrowCode(String borrowCode);
}
