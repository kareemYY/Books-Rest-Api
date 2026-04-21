package com.luv2code.books.service;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;


    public List<BookDto> getAllBooks(){
        return  convertToBookDtos(bookRepository.findAll());
    }

    public List<BookDto> getAllBooksWithCategory(String category){
        return  convertToBookDtos(bookRepository.findByCategory(category));
    }



    public BookDto getBookById(long id){
        return convertToBookDto(bookRepository.findById(id).orElseThrow(
                ()->new BookNotFoundException("Book with id "+id+" not found!")));
    }

    public BookDto createBook(BookDto newBookDto){
        newBookDto.setId(0);
        Book newBook = convertToBook(newBookDto);
        newBook = bookRepository.save(newBook);
        return convertToBookDto(newBook);
    }

    public BookDto updateBook(long id ,BookDto updateBookDto){
        updateBookDto.setId(id);
        Book updateBook= bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book With "+id+" not found"));
        updateBook.setAuthor(updateBookDto.getAuthor());
        updateBook.setTitle(updateBookDto.getTitle());
        updateBook.setCategory(updateBookDto.getCategory());
        updateBook.setRating(updateBookDto.getRating());

        Book updatedBook = bookRepository.save(updateBook);
      return convertToBookDto(updatedBook);
    }


    public String deleteBook( long id ){
      Book book = bookRepository.findById(id).
              orElseThrow(() -> new BookNotFoundException("Book With "+id+" not found"));
      bookRepository.delete(book);
      return "Book with id "+id+" has been deleted";
    }





    private Book convertToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getCategory(),
                bookDto.getRating()
        );
    }

    private BookDto convertToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getRating()
        );
    }

    private List<Book> convertToBooks(List<BookDto> listDto){
        List<Book> books = new ArrayList<>();
        for (BookDto bookDto : listDto) {
            books.add(convertToBook(bookDto));
        }
        return books;
    }

    private List<BookDto> convertToBookDtos(List<Book> list){
        return list.stream().map(this::convertToBookDto).toList();
    }





}
