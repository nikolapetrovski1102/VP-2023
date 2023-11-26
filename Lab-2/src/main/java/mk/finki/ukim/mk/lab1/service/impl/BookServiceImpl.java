package mk.finki.ukim.mk.lab1.service.impl;

import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.repository.AuthorRepository;
import mk.finki.ukim.mk.lab1.repository.BookRepository;
import mk.finki.ukim.mk.lab1.service.AuthorService;
import mk.finki.ukim.mk.lab1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements AuthorService, BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return listAuthors().stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book findBookByIsbn(String isbn) {
        return listBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }
    public List<Book> addNewBook(Book book){
        List<Book> bookList = listBooks();
        bookList.add(book);
        return bookList;
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = findById(authorId);
        Book book = findBookByIsbn(isbn);

        if (book != null){
            book.getAuthors().add(author);
        }

        return author;
    }
}