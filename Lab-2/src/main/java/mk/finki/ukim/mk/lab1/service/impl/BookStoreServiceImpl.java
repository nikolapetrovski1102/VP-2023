package mk.finki.ukim.mk.lab1.service.impl;

import mk.finki.ukim.mk.lab1.models.BookStore;
import mk.finki.ukim.mk.lab1.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab1.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }
}
