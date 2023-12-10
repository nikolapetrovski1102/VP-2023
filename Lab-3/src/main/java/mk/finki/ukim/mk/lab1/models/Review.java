package mk.finki.ukim.mk.lab1.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;
    private String description;

    @ManyToOne
    private Book book;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public Review(Integer score, String description, Book book, LocalDateTime timestamp) {
        this.score = score;
        this.description = description;
        this.book = book;
        this.timestamp = timestamp;
    }
}