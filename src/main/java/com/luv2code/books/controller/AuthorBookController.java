package com.luv2code.books.controller;

import com.luv2code.books.dto.request.BookCreateRequest;
import com.luv2code.books.dto.response.BookResponse;
import com.luv2code.books.service.books.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/author")
@Tag(name = "Author Book Control REST API Endpoint",description = "Operation of managing books of author")
public class AuthorBookController {


    private final BookService bookService;

    public AuthorBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(summary = "Creating new book ",description = "Create book for sign user")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        return bookService.createNewBook(bookCreateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books by author", description = "Get all books filter by author")
    @GetMapping
    public List<BookResponse> findAllBooksForOwner(){
        return bookService.findAllBooksForOwner();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deleting Book ",description = "Delete book by title ")
    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title){
        bookService.deleteBookByTitle(title);
    }
}
