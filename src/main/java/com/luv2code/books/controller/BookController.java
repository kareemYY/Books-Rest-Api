package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();


    public BookController() {
        initializeBooks();
    }
    private void initializeBooks(){
        books.addAll(List.of(
                new Book ("Title one","Author one" , "Science"),
                new Book("Title two","Author Two","Science"),
                new Book("Title three","Author Three","history"),
                new Book ("Title four","Author four" , "Science"),
                new Book("Title five","Author five","math"),
                new Book("Title six","Author six","math")
        ));
    }

    @GetMapping("/api")
    public List<Book> getBooks() {
        return books;
    }

}
