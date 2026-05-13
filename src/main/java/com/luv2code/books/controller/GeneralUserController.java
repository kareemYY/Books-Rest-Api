package com.luv2code.books.controller;


import com.luv2code.books.dto.response.BookResponse;
import com.luv2code.books.service.books.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Api EndPoint For General User",description = "This EndPoint For All User With Not Admin Or Author")
@RestController
@RequestMapping("/api/books")
public class GeneralUserController {



    private final BookService bookService;

    public GeneralUserController(BookService bookService) {
        this.bookService = bookService;
    }


    @Operation(summary = "Get all books",description = "Get all book for all authors")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<BookResponse> bookResponses(){
       return bookService.findAllBooksForAnonymousUser();
    }


    @Operation(summary = "Review a book " ,description = "Adding rate for book")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{title}/{rating}")
    public BookResponse addingRatingForBook(@PathVariable  String title,@PathVariable@Min(1) @Max(5) int rating){
       return bookService.ratingBookByTitle(title,rating);
    }


}
