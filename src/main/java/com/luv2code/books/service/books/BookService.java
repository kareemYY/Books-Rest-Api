package com.luv2code.books.service.books;

import com.luv2code.books.dto.request.BookCreateRequest;
import com.luv2code.books.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse createNewBook(BookCreateRequest bookCreateRequest);

    List<BookResponse> findAllBooksForOwner();

    List<BookResponse> findAllBooksForAnonymousUser();

    void deleteBookByTitle(String title);

    void ratingBookByTitle(String title);


}
