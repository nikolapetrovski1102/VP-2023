package mk.finki.ukim.mk.lab1.repository.jpa;

import mk.finki.ukim.mk.lab1.models.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookStoreRepository extends JpaRepository<BookStore, Long> {
}
