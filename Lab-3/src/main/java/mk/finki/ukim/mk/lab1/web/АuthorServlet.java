package mk.finki.ukim.mk.lab1.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab1.models.Author;
import mk.finki.ukim.mk.lab1.repository.AuthorRepository;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaAuthorRepository;
import mk.finki.ukim.mk.lab1.service.impl.AuthorServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "authors", urlPatterns = "/author")
public class АuthorServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final JpaAuthorRepository jpaAuthorRepository;

    public АuthorServlet(SpringTemplateEngine springTemplateEngine, JpaAuthorRepository jpaAuthorRepository) {
        this.springTemplateEngine = springTemplateEngine;
        this.jpaAuthorRepository = jpaAuthorRepository;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String bookIsbn = req.getParameter("bookIsbn");

        context.setVariable("authors", jpaAuthorRepository.findAll());
        context.setVariable("bookIsbn", bookIsbn);

        springTemplateEngine.process("authorList.html", context, resp.getWriter());
    }
}
