package mk.finki.ukim.mk.lab1.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    private
        String name;
        String surname;
}
