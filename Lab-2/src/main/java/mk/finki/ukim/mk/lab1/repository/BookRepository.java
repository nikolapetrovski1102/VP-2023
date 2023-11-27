package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.service.impl.BookStoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books = new ArrayList<>();
    private BookStoreServiceImpl bookStoreService;

    public BookRepository(List<Book> books, BookStoreServiceImpl bookStoreService) {
        this.bookStoreService = bookStoreService;
        List<BookStore> bookStores = bookStoreService.findAll();
        books.add(new Book("ISBN001", "The Great Adventure", "Adventure", 2005, bookStores.get(1).getId(), bookStores.get(1)));
        books.add(new Book("ISBN002", "Mystery of the Lost Key", "Mystery", 2010, bookStores.get(2).getId(), bookStores.get(2)));
        books.add(new Book("ISBN003", "Love in the Time of Rain", "Romance", 2018, bookStores.get(3).getId(), bookStores.get(3)));
        books.add(new Book("ISBN004", "Beyond the Horizon", "Science Fiction", 2022, bookStores.get(0).getId(), bookStores.get(0)));
        books.add(new Book("ISBN005", "History Revisited", "Historical Fiction", 2015, bookStores.get(4).getId(), bookStores.get(4)));
        this.books = books;
    }

    public List<Book> findAll(){
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
