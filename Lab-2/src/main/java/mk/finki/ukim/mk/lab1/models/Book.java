package mk.finki.ukim.mk.lab1.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors = new ArrayList<>();
    private BookStore bookStore;
    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.id = (long) (Math.random() * 1000 );
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore = bookStore;
    }
}