package com.luv2code.books.controller;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public List<BookDto> getBooks(@Parameter(description = "Optional query parameter")
                                   @RequestParam(required = false) String category) {

        if(category == null){return bookService.getAllBooks();}

        return bookService.getAllBooksWithCategory(category);
    }

    @Operation(summary = "Get A book By Id ", description = "Retrieve a specific book by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BookDto getBookById(@Parameter(description = "Id of book to be retrieved")
                                   @PathVariable @Min(value = 1) long id) {
        return bookService.getBookById(id);
    }


    @Operation(summary = "Create A new Book ", description = "Add a new book to the list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook( @Valid @RequestBody BookDto newBookDto){
         return bookService.createBook(newBookDto);
    }


    @Operation(summary = "Update a book ", description = "Update the details of an book ")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public BookDto updateBook(@Parameter(description = "Id of the book to update")
                                  @PathVariable @Min(value = 1) long id,@Valid @RequestBody BookDto updateBookDto){
        return bookService.updateBook(id,updateBookDto);
    }



    @Operation(summary = "delete a book ", description = "delete a book from the list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "Id of the book to delete")
                               @PathVariable @Min(value = 1) long id){
        bookService.deleteBook(id);
    }













}
