package ra.eu.library_kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.eu.library_kata.entity.BookEntity;
import ra.eu.library_kata.entity.BorrowedBookEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBookEntity, Long> {

    Optional<BorrowedBookEntity> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);

    @Query("SELECT bb.book FROM BorrowedBookEntity bb WHERE bb.user.id = :userId AND bb.returnDate IS NULL")
    List<BookEntity> findBooksByUserId(Long userId);
}
