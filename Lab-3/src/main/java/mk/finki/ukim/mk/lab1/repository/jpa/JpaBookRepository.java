package mk.finki.ukim.mk.lab1.repository.jpa;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query("INSERT INTO Book (isbn, title, genre, year, book_store_id) " +
            "VALUES (:isbn, :title, :genre, :year, :bookStoreId)")
    void addNewBook(@Param("isbn") String isbn, @Param("title") String title,
                    @Param("genre") String genre, @Param("year") Integer year,
                    @Param("bookStoreId") Long bookStoreId);

    @Modifying
    @Transactional
    @Query("update Book set title = :title, genre = :genre, year = :year where id = :id")
    void editBook(@Param("title") String title, @Param("genre") String genre, @Param("year") Integer year, @Param("id") Long id);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Book findBookByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findBookById(@Param("id") Long id);

}
