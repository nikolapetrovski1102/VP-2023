package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books = new ArrayList<>();
    private BookStoreServiceImpl bookStoreService;

    public BookRepository(List<Book> books, BookStoreServiceImpl bookStoreService) {
        List<BookStore> bookStore = bookStoreService.findAll();
        books.add(new Book("ISBN001", "The Great Adventure", "Adventure", 2005, bookStore.get(0)));
        books.add(new Book("ISBN002", "Mystery of the Lost Key", "Mystery", 2010, bookStore.get(1)));
        books.add(new Book("ISBN003", "Love in the Time of Rain", "Romance", 2018, bookStore.get(2)));
        books.add(new Book("ISBN004", "Beyond the Horizon", "Science Fiction", 2022, bookStore.get(3)));
        books.add(new Book("ISBN005", "History Revisited", "Historical Fiction", 2015, bookStore.get(4)));
        this.books = books;
    }

    public BookRepository() {

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
