package com.luv2code.books.service;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.mapper.BookMapper;
import com.luv2code.books.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {


   private final BookRepository bookRepository;

   private final BookMapper bookMapper;


    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks(String category){
        if (category == null){return  bookMapper.convertToBookDtos(bookRepository.findAll());}
        else {return bookMapper.convertToBookDtos(bookRepository.findByCategory(category));}

    }




    public BookDto getBookById(long id){
        return bookMapper.convertToBookDto(bookRepository.findById(id).orElseThrow(
                ()->new BookNotFoundException("Book with id "+id+" not found!")));
    }

    public BookDto createBook(BookDto newBookDto){
        newBookDto.setId(0);
        Book newBook = bookMapper.convertToBook(newBookDto);
        newBook = bookRepository.save(newBook);
        return bookMapper.convertToBookDto(newBook);
    }

    public BookDto updateBook(long id ,BookDto updateBookDto){
        Book updateBook= bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book With "+id+" not found"));
        updateBook.setAuthor(updateBookDto.getAuthor());
        updateBook.setTitle(updateBookDto.getTitle());
        updateBook.setCategory(updateBookDto.getCategory());
        updateBook.setRating(updateBookDto.getRating());

        Book updatedBook = bookRepository.save(updateBook);
      return bookMapper.convertToBookDto(updatedBook);
    }


    public String deleteBook( long id ){
      Book book = bookRepository.findById(id).
              orElseThrow(() -> new BookNotFoundException("Book With "+id+" not found"));
      bookRepository.delete(book);
      return "Book with id "+id+" has been deleted";
    }



}
