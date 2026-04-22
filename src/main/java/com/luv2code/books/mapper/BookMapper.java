package com.luv2code.books.mapper;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {



    public Book convertToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCategory(),
                bookDto.getRating()
        );
    }

    public BookDto convertToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getRating()
        );
    }

    public List<Book> convertToBooks(List<BookDto> listDto){
        List<Book> books = new ArrayList<>();
        for (BookDto bookDto : listDto) {
            books.add(convertToBook(bookDto));
        }
        return books;
    }

    public List<BookDto> convertToBookDtos(List<Book> list){
        return list.stream().map(this::convertToBookDto).toList();
    }

    public Book updateToBook(BookDto bookDto, Book book){
       book.setTitle(bookDto.getTitle());
       book.setAuthor(bookDto.getAuthor());
       book.setCategory(bookDto.getCategory());
       book.setRating(bookDto.getRating());
       return book;
    }
}
