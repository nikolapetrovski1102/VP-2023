package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books = new ArrayList<>();

    public List<Book> findAll(){
        books.add(new Book("ISBN001", "The Great Adventure", "Adventure", 2005));
        books.add(new Book("ISBN002", "Mystery of the Lost Key", "Mystery", 2010));
        books.add(new Book("ISBN003", "Love in the Time of Rain", "Romance", 2018));
        books.add(new Book("ISBN004", "Beyond the Horizon", "Science Fiction", 2022));
        books.add(new Book("ISBN005", "History Revisited", "Historical Fiction", 2015));
        return books;
    }

    public Book findByIsbn(String isbn){
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);

        return author;
    }

}
