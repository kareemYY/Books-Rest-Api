package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api")
    public List<Book> getBooks() {
        return bookService.initializeBooks();
    }

}
