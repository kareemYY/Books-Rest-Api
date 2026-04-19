package com.luv2code.books.controller;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Books Rest API Endpoints" ,description = "Operations related to books")
@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @Operation(summary = "Get All Books " , description = "Retrieve a list of available books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if(category == null){return bookService.getAllBooks();}

        return bookService.getAllBooks().stream().filter(
                book -> book.getCategory().equalsIgnoreCase(category)
        ).toList();

    }

    @Operation(summary = "Get A book By Id ", description = "Retrieve a specific book by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookByTitle(@PathVariable @Min(value = 1) long id) {
        return bookService.getBookById(id);
    }


    @Operation(summary = "Create A new Book ", description = "Add a new book to the list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book createBook( @Valid @RequestBody BookDto newBookDto){
         return bookService.createBook(newBookDto   );
    }


    @Operation(summary = "Update a book ", description = "Update the details of an book ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable @Min(value = 1) long id,@Valid @RequestBody BookDto updateBookDto){
        return bookService.updateBook(id,updateBookDto);
    }



    @Operation(summary = "delete a book ", description = "delete a book from the list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(value = 1) long id){
        bookService.deleteBook(id);
    }













}
