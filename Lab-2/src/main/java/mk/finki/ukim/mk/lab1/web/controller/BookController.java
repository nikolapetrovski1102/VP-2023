package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import mk.finki.ukim.mk.lab1.repository.BookRepository;
import mk.finki.ukim.mk.lab1.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.io.IOException;

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
}
