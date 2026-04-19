package com.luv2code.books.service;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.addAll(List.of(
                new Book(1,"Title one"  ,"Author one"   , "Science" ,3),
                new Book(2,"Title two"  ,"Author two"   , "Math"    ,5),
                new Book(3,"Title three","Author three" , "Math"    ,6),
                new Book(4,"Title four" ,"Author four"  , "History" ,1),
                new Book(5,"Title five" ,"Author five"  , "Math"    ,9),
                new Book(6,"Title six"  ,"Author six"   , "Science" ,6)
        ));
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookById(long id){
        return books.stream()
                .filter(book -> book.getId()==id)
                .findFirst().orElse(null);
    }

    public Book createBook(BookDto newBookDto){
        long id =books.isEmpty()? 1: books.getLast().getId()+1;

        Book book =convertToBook(id,newBookDto);
        books.add(book);
        return book;
    }

    public BookDto updateBook(long id ,BookDto updateBookDto){
        for (int i = 0 ; i < books.size() ; i++){
            if (books.get(i).getId()==id){
                Book updatedBook = convertToBook(id , updateBookDto);
                books.set(i, updatedBook);
                return convertToBookDto(id, updatedBook);
            }
        }
        return null;
    }


    public void deleteBook( long id ){
        books.removeIf(book -> book.getId() == id);
    }





    private Book convertToBook(long id,BookDto bookDto){
        return new Book(
                id,
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCategory(),
                bookDto.getRating()
        );
    }

    private BookDto convertToBookDto(long id,Book book){
        return new BookDto(
                id,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getRating()
        );
    }








}
