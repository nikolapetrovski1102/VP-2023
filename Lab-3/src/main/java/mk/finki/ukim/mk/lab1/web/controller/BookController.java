package mk.finki.ukim.mk.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab1.models.Book;
import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.models.Review;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaBookRepository;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaBookStoreRepository;
import mk.finki.ukim.mk.lab1.repository.jpa.JpaReview;
import mk.finki.ukim.mk.lab1.service.impl.ReviewServiceImpl;
import org.h2.engine.Mode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookController {

    private final JpaBookRepository jpaBookRepository;
    private final JpaBookStoreRepository jpaBookStoreRepository;
    private final JpaReview jpaReview;
    private final ReviewServiceImpl reviewService;

    public BookController(JpaBookRepository jpaBookRepository, JpaBookStoreRepository jpaBookStoreRepository, JpaReview jpaReview, ReviewServiceImpl reviewService) {
        this.jpaBookRepository = jpaBookRepository;
        this.jpaBookStoreRepository = jpaBookStoreRepository;
        this.jpaReview = jpaReview;
        this.reviewService = reviewService;
    }

    @RequestMapping("/books")
        public String getBooksPage(@RequestParam(required = false) String error, Model model){

            model.addAttribute("books", jpaBookRepository.findAll());

            return "listBooks.html";
        }

    @RequestMapping("/books/add")
    public String addBookView (@RequestParam(required = false) String error, Model model){

        model.addAttribute("bookStores", this.jpaBookStoreRepository.findAll());
        model.addAttribute("bookForm", new Book());

        return "addBook";
    }

    @PostMapping("/books/save")
    public String saveBook(Book bookForm, BindingResult result, Model model)  {
        String isbn = bookForm.getIsbn();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();
        Long selectedBookStoreId = bookForm.getBook_store_id();

        BookStore bookStore = jpaBookStoreRepository.findById(selectedBookStoreId)
                .orElseThrow(() -> new IllegalArgumentException("No valid id for book store: " + selectedBookStoreId));

        Book book = new Book(isbn, title, genre, year, selectedBookStoreId, bookStore);

        jpaBookRepository.addNewBook(isbn, title, genre, year, selectedBookStoreId);

        List<Book> listBooks = jpaBookRepository.findAll();

        return "redirect:/books";
    }

    @GetMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model){

        Book foundBook = jpaBookRepository.findAll().stream().filter(book1 -> book1.getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book id: " + bookId));

        model.addAttribute("book", foundBook);

        return "editBook.html";
    }

    @PostMapping("/books/edit")
    public String editedBook(Book bookForm, Model model){
        Long id = bookForm.getId();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();

        if (jpaBookRepository.existsById(id)){
            jpaBookRepository.editBook(title, genre, year, id);
        }

        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model){

        jpaBookRepository.deleteById(id);

        return "redirect:/books";
    }

    @GetMapping("/books/reviews/{id}")
    public String bookReviews(@PathVariable Long id, Model model) {
        List<Review> review = jpaReview.getReviewByBook(id);

        model.addAttribute("bookReviews", review);
        int sumScore = 0;
        for (int i = 0; i < review.size(); i++) {
            sumScore += review.get(i).getScore();
        }
        float overallScore = (float) sumScore / review.size();

        model.addAttribute("overallScore", overallScore);

        return "review";
    }

    @GetMapping("/books/add-review/{id}")
    public String addBookReview(@PathVariable Long id, Model model) {

        model.addAttribute("bookFor", id);
        model.addAttribute("reviewForm", new Review());

        return "add-review";
    }

    @PostMapping("/books/save-review")
    public String saveBookReview(Review reviewModel, @RequestParam("book.id") Long bookId) {
        System.out.println("Book ID from hidden input: " + bookId);

        Book book = jpaBookRepository.findById(bookId).orElse(null);

            reviewService.addReview(reviewModel.getScore(), reviewModel.getDescription(), book, reviewModel.getTimestamp());

        return "redirect:/books";
    }

    @GetMapping("/books/sort-reviews/{id}")
    public String sortReviews(
            @RequestParam("timeFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
            @RequestParam("timeTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo,
            @PathVariable("id") Long bookId, Model model) {

        List<Review> reviewList = reviewService.sortByDateTime(timeFrom, timeTo, bookId);

        model.addAttribute("bookReviews", reviewList);

        int sumScore = 0;

        for (int i = 0; i < reviewList.size(); i++){
            sumScore += reviewList.get(i).getScore();
        }

        float overallScore = sumScore / reviewList.size();

        model.addAttribute("overallScore", overallScore);

        return "review";
    }


}