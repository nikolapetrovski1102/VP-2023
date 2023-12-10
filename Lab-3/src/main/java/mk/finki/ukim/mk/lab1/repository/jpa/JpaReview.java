package mk.finki.ukim.mk.lab1.repository.jpa;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaReview extends JpaRepository <Review, Long> {

    @Query("SELECT r from Review r where r.book.id = :book_id")
    List<Review> getReviewByBook(@Param("book_id") Long bookId);


}
