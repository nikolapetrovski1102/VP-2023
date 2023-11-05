package mk.finki.ukim.mk.lab1.repository;

import mk.finki.ukim.mk.lab1.models.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    List<Author> authors = new ArrayList<>();

    public List<Author> findAll(){
        authors.add(new Author(1L, "Nikola", "Petrovski", "Some biography"));
        authors.add(new Author(2L, "Elena", "Ivanov", "A passionate writer with a love for storytelling."));
        authors.add(new Author(3L, "Alexander", "Johnson", "An adventurous spirit who finds inspiration in nature."));
        authors.add(new Author(4L, "Sophia", "Smith", "A prolific author known for her eloquent prose."));
        authors.add(new Author(5L, "Daniel", "Brown", "A dedicated writer exploring the complexities of human emotions."));
        return authors;
    }

    public Optional<Author> findById(Long id){
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }

}
