package mk.finki.ukim.mk.lab1.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book_authors")
public class BookAuthors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;
}
