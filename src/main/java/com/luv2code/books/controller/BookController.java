package com.luv2code.books.controller;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if(category == null){return bookService.getAllBooks();}

        return bookService.getAllBooks().stream().filter(
                book -> book.getCategory().equalsIgnoreCase(category)
        ).toList();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookByTitle(@PathVariable @Min(value = 1) long id) {
        return bookService.getBookById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book createBook( @Valid @RequestBody BookDto newBookDto){
         return bookService.createBook(newBookDto   );
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable @Min(value = 1) long id,@Valid @RequestBody BookDto updateBookDto){
        return bookService.updateBook(id,updateBookDto);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(value = 1) long id){
        bookService.deleteBook(id);
    }













}
