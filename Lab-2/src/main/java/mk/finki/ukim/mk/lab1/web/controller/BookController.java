package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.repository.BookRepository;
import mk.finki.ukim.mk.lab1.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private BookStoreRepository bookStoreRepository;

    public BookController(BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @RequestMapping("/books")
        public String getBooksPage(@RequestParam(required = false) String error, Model model){

            model.addAttribute("books", bookRepository.findAll());

            return "listBooks.html";
        }

    @RequestMapping("/books/add")
    public String addBookView (@RequestParam(required = false) String error, Model model){

        model.addAttribute("bookStores", bookStoreRepository.findAll());
        model.addAttribute("bookForm", new Book());

        return "addBook.html";
    }

    @PostMapping("/books/save")
    public String saveBook(Book bookForm, BindingResult result, Model model)  {
        String isbn = bookForm.getIsbn();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();
        Long selectedBookStoreId = bookForm.getBookStoreId();

        BookStore selectedBookStore = (BookStore) bookStoreRepository.findAll().stream().filter(bookStore1 -> bookStore1.getId().equals(selectedBookStoreId)).findFirst().orElse(null);

        List<Book> existingBooks = bookRepository.findAll();
        existingBooks.add(new Book(isbn, title, genre, year, selectedBookStoreId, selectedBookStore));

        model.addAttribute("books", existingBooks);

        return "listBooks.html";
    }

    @GetMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model){

        Book foundBook = bookRepository.findAll().stream().filter(book1 -> book1.getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book id: " + bookId));

        model.addAttribute("book", foundBook);

        return "editBook.html";
    }

    @PostMapping("/books/edit")
    public String editedBook(Book bookForm, Model model){
        String isbn = bookForm.getIsbn();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();

        Book book = bookRepository.findAll().stream().filter(book1 -> book1.getIsbn().equals(isbn)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book id: " + isbn));

        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);

        model.addAttribute("books", bookRepository.findAll());

        return "listBooks.html";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model){

        bookRepository.findAll().removeIf(book -> book.getId().equals(id));

        model.addAttribute("books", bookRepository.findAll());

        return "listBooks.html";
    }

}