package com.luv2code.books.service;

import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.mapper.BookMapper;
import com.luv2code.books.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<BookDto> getAllBooks(int page, int size, String category) {
        Pageable pageable =PageRequest.of(page, size);

        if (category == null||category.isBlank()){
            Page<Book> booksPage = bookRepository.findAll(pageable);
            return booksPage.map(bookMapper::convertToBookDto);}
        else {
            Page<Book> booksPageByCategory = bookRepository.findByCategory(category,pageable);
            return booksPageByCategory.map(bookMapper::convertToBookDto);}
    }


    public boolean checkIfBookExistsByTitle(String title) {
        return bookRepository.existsByTitle(title);
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
                orElseThrow(() -> new BookNotFoundException("Book with id " +id+ " not found!"));
        bookMapper.updateToBook(updateBookDto,updateBook);
        Book updatedBook = bookRepository.save(updateBook);
        return bookMapper.convertToBookDto(updatedBook);
    }


    public String deleteBook( long id ){
      Book book = bookRepository.findById(id).
              orElseThrow(() -> new BookNotFoundException("Book with id " +id+ " not found!"));
      bookRepository.delete(book);
      return "Book with id "+id+" has been deleted";
    }



}
