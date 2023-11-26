package mk.finki.ukim.mk.lab1.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<Book> listBooks;

    public BookStore(String name, String city, String address, List<Book> books) {
        this.id = (long)(Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
        this.listBooks = new ArrayList<>();
    }
}
