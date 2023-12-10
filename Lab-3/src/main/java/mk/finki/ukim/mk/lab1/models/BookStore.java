package mk.finki.ukim.mk.lab1.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BookStore")
public class BookStore {

    private
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String name;
        String city;
        String address;
        @OneToMany
        List<Book> listBooks = new ArrayList<>();

    public BookStore(String name, String city, String address) {
        this.id = (long)(Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
