package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.service.impl.BookServiceImpl;
import mk.finki.ukim.mk.lab1.service.impl.BookStoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookStoreRepository {

    List<BookStore> listBookStores = new ArrayList<>();

    public BookStoreRepository(List<BookStore> listBookStores) {
        listBookStores.add(new BookStore("Book Haven", "New York", "123 Main St"));
        listBookStores.add(new BookStore("City Books", "Los Angeles", "456 Oak Ave"));
        listBookStores.add(new BookStore("Read It Again", "Chicago", "789 Elm Blvd"));
        listBookStores.add(new BookStore("Book Nook", "San Francisco", "101 Pine Lane"));
        listBookStores.add(new BookStore("Page Turner", "Seattle", "222 Maple Rd"));
        this.listBookStores = listBookStores;
    }

    public List<BookStore> findAll(){
        return listBookStores;
    }

}
