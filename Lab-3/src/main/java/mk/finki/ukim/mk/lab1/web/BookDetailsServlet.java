package mk.finki.ukim.mk.lab1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaAuthorRepository;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab1.service.impl.AuthorServiceImpl;
import mk.finki.ukim.mk.lab1.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookDetails", urlPatterns = "/bookDetails")
public class BookDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final JpaAuthorRepository jpaAuthorRepository;
    private final JpaBookRepository jpaBookRepository;

    public BookDetailsServlet(SpringTemplateEngine springTemplateEngine, JpaBookRepository jpaBookRepository, JpaAuthorRepository jpaAuthorRepository) {
        this.springTemplateEngine = springTemplateEngine;
        this.jpaBookRepository = jpaBookRepository;
        this.jpaAuthorRepository = jpaAuthorRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String bookIsbn = req.getParameter("bookIsbn");
        String authorId = req.getParameter("authorId");


        Book book = jpaBookRepository.findBookByIsbn(bookIsbn);

        Author author = jpaAuthorRepository.findById(Long.parseLong(authorId))
                .orElseThrow(() -> new IllegalArgumentException("No valid id for author: " + authorId));

        book.addAuthor(author);

        jpaBookRepository.save(book);

        context.setVariable("detailsBook", book);

        springTemplateEngine.process("bookDetails.html", context, resp.getWriter());

    }
}
