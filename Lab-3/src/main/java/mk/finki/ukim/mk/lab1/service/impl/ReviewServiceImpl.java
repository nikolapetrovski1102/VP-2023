package mk.finki.ukim.mk.lab1.service.impl;

import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.Review;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaReview;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl{

    private final JpaReview jpaReview;
    private final JpaBookRepository jpaBookRepository;

    public ReviewServiceImpl(JpaReview jpaReview, JpaBookRepository jpaBookRepository) {
        this.jpaReview = jpaReview;
        this.jpaBookRepository = jpaBookRepository;
    }

    public Review addReview (int score, String description, Book book, LocalDateTime timestamp){
        Review newReview = new Review(score, description, book, timestamp);

        return jpaReview.save(newReview);
    }

    public List<Review> sortByDateTime (LocalDateTime timeFrom, LocalDateTime timeTo, Long bookId){

        return jpaReview.findAll().stream().filter(review -> review.getId().equals(bookId) && review.getTimestamp().isAfter(timeFrom) || review.getTimestamp().isBefore(timeTo)).toList();
    }

}
