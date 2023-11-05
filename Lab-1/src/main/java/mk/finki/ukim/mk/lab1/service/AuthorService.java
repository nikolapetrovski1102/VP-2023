package mk.finki.ukim.mk.lab1.service;

import mk.finki.ukim.mk.lab1.models.Author;

import java.util.List;

public interface AuthorService{
    List<Author> listAuthors();
    Author findById(Long id);
}
