package mk.finki.ukim.mk.lab1.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    String isbn;
    String title;
    String genre;
    int year;
    List<Author> authors = new ArrayList<>();

    public Book(String isbn, String title, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }
}