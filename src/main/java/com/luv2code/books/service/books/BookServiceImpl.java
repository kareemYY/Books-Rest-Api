package com.luv2code.books.service.books;

import com.luv2code.books.dto.request.BookCreateRequest;
import com.luv2code.books.dto.response.BookResponse;
import com.luv2code.books.entity.Book;
import com.luv2code.books.entity.User;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.repository.BookRepository;
import com.luv2code.books.util.FindAuthenticatedUser;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public BookServiceImpl(BookRepository bookRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.bookRepository = bookRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Transactional
    @Override
    public BookResponse createNewBook(BookCreateRequest bookCreateRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Book book = new Book(bookCreateRequest.getTitle(),
                currentUser.getFullName(),
                bookCreateRequest.getCategory(),
                new ArrayList<>(),
                currentUser);
        Book savedBook = bookRepository.save(book);

        return convertBookToBookResponse(savedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResponse> findAllBooksForOwner() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        List<Book> books =bookRepository.findByOwner(currentUser);
        List<BookResponse> bookResponses =books.stream().map(this::convertBookToBookResponse).toList();
        return bookResponses;
    }


    @Transactional(readOnly = true)
    @Override
    public List<BookResponse> findAllBooksForAnonymousUser() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = books.stream()
                .map(this :: convertBookToBookResponse).toList();
        return bookResponses;
    }

    @Transactional
    @Override
    public void deleteBookByTitle(String title) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(()-> new BookNotFoundException("This Book Not Found : " + title));
        if (book.getAuthor().equals(currentUser.getFullName())||isAdmin(currentUser)) {
            bookRepository.delete(book);
            return;
        }
        throw new AuthorizationDeniedException("You are not allowed to delete this book");
    }

    @Transactional
    @Override
    public void ratingBookByTitle(String title) {


    }


    private BookResponse convertBookToBookResponse(Book book) {
        return new BookResponse(book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getRatingAvg(),
                book.getCreateAt());
    }
    private boolean isAdmin(User user){
        return user.getAuthorities().stream()
                .anyMatch(authority ->"ROLE_ADMIN".equals(authority.getAuthority()));
    }

    private boolean isAuthor(User user){
        return user.getAuthorities().stream()
                .anyMatch(authority ->"ROLE_AUTHOR".equals(authority.getAuthority()));
    }





}
