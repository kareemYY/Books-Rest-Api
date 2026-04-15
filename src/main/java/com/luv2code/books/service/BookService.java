package com.luv2code.books.service;

import com.luv2code.books.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public List<Book> initializeBooks(){
        books.addAll(List.of(
                new Book("Title one","Author one" , "Science"),
                new Book("Title two","Author Two","Science"),
                new Book("Title three","Author Three","history"),
                new Book ("Title four","Author four" , "Science"),
                new Book("Title five","Author five","math"),
                new Book("Title six","Author six","math")
        ));
        return books;
    }

}
