package mk.finki.ukim.mk.lab1.repository.jpa;

import mk.finki.ukim.mk.lab1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface JpaAuthorRepository extends JpaRepository<Author, Long> {
}
