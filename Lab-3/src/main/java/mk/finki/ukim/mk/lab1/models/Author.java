package mk.finki.ukim.mk.lab1.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String biography;

    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDate dateOfBirth;

    @ManyToOne
    private Book book;

    public Author(Long id, String name, String surname, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}
