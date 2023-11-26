package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookStoreRepository {

    private List<BookStore> listBookStores = new ArrayList<>();
    private BookServiceImpl bookService;

    public BookStoreRepository(List<BookStore> listBookStores, BookServiceImpl bookService) {
        List<Book> books = bookService.listBooks();
        listBookStores.add(new BookStore("Book Haven", "New York", "123 Main St", books.subList(0, 1)));
        listBookStores.add(new BookStore("City Books", "Los Angeles", "456 Oak Ave", books.subList(0, 3)));
        listBookStores.add(new BookStore("Read It Again", "Chicago", "789 Elm Blvd", books.subList(0, 3)));
        listBookStores.add(new BookStore("Book Nook", "San Francisco", "101 Pine Lane", books.subList(0, 4)));
        listBookStores.add(new BookStore("Page Turner", "Seattle", "222 Maple Rd", books.subList(0, 4)));
    }

    public BookStoreRepository() {

    }

    public List<BookStore> findAll(){
        return listBookStores;
    }

}
