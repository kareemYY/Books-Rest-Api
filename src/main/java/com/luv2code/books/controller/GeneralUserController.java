package com.luv2code.books.controller;


import com.luv2code.books.dto.response.BookResponse;
import com.luv2code.books.service.books.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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


}
