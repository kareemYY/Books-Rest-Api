package com.luv2code.books.controller;

import com.luv2code.books.dto.BookDto;
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

    @GetMapping("/{id}")
    public Book getBookByTitle(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody BookDto newBookDto){
         return bookService.createBook(newBookDto   );
    }


    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable long id, @RequestBody BookDto updateBookDto){
        return bookService.updateBook(id,updateBookDto);
    }



    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }













}
