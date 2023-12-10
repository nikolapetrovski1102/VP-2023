package mk.finki.ukim.mk.lab1.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String genre;
    private int year;

    @ManyToOne
    private BookStore bookStore;

    @Column(insertable = false, updatable = false)
    private Long book_store_id;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    public Book(String isbn, String title, String genre, int year, Long book_store_id, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.book_store_id = book_store_id;
        this.bookStore = bookStore;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.setBook(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.setBook(null);
    }
}
