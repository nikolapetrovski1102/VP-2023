package mk.finki.ukim.mk.lab1.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String biography;
    public List<Book> bookList = new ArrayList<>();

    public Author(Long id, String name, String surname, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}