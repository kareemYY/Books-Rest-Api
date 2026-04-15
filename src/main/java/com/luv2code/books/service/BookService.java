package com.luv2code.books.service;

import com.luv2code.books.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.addAll(List.of(
                new Book("Title one","Author one" , "Science"),
                new Book("Title two","Author Two","Science"),
                new Book("Title three","Author Three","history"),
                new Book ("Title four","Author four" , "Science"),
                new Book("Title five","Author five","math"),
                new Book("Title six","Author six","math")
        ));
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookByTitle(String title){
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst().orElse(null);
    }

    public Book createBook(Book newBook){
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if(isNewBook){books.add(newBook);
        return newBook;
        }
        return null;
    }

    public Book updateBook(String  title ,Book newBook){
        for (int i = 0 ; i < books.size() ; i++){
            if (books.get(i).getTitle().equalsIgnoreCase(title)){
                books.set(i, newBook);
                return newBook;
            }
        }
        return null;
    }


}
