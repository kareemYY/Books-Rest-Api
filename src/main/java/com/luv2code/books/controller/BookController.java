package com.luv2code.books.controller;

import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if(category == null){return bookService.getAllBooks();}

        return bookService.getAllBooks().stream().filter(
                book -> book.getCategory().equalsIgnoreCase(category)
        ).toList();

    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping
    public Book createBook(@RequestBody Book newBook){
         return bookService.createBook(newBook);
    }


    @PutMapping("/{title}")
    public Book updateBook(@PathVariable String title, @RequestBody Book newBook){
        return bookService.updateBook(title,newBook);
    }



    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title){
        bookService.deleteBook(title);
    }













}
