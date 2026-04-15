package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if(category == null){return bookService.getAllBooks();}

        return bookService.getAllBooks().stream().filter(
                book -> book.getCategory().equalsIgnoreCase(category)
        ).toList();

    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody Book newBook){
         return bookService.createBook(newBook);
    }


    @PutMapping("/api/books/{title}")
    public Book updateBook(@PathVariable String title, @RequestBody Book newBook){
        return bookService.updateBook(title,newBook);
    }
















}
