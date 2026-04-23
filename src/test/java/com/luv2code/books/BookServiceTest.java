package com.luv2code.books;


import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.repository.BookRepository;
import com.luv2code.books.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    @Value("${sql.script.create.book1}")
    private String createBook1;

    @Value("${sql.script.create.book2}")
    private String createBook2;

    @Value("${sql.script.create.book3}")
    private String createBook3;

    @Value("${sql.script.create.book4}")
    private String createBook4;

    @Value("${sql.script.delete.book}")
    private String deleteBook;



    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(createBook1);
        jdbc.execute(createBook2);
        jdbc.execute(createBook3);
        jdbc.execute(createBook4);
    }



    @Test
    public void checkBookById(){
        BookDto book = bookService.getBookById(1);
        assertNotNull(book);
        assertEquals(1,book.getId());
    }

    @Test
    public void checkBookSizeWithCategoryNull(){
        Page<BookDto> bookDtos=bookService.getAllBooks(0,2,null);
        assertNotNull(bookDtos);
        assertEquals(2,bookDtos.getSize());
        assertEquals(4,bookDtos.getTotalElements());
    }

    @Test
    public void checkBookSizeWithMathCategory(){
        Page<BookDto> bookDtos=bookService.getAllBooks(0,2,"math");
        assertNotNull(bookDtos);
        assertEquals(2,bookDtos.getSize());
        assertEquals(2,bookDtos.getTotalElements());
        assertEquals(1,bookDtos.getTotalPages());
        assertEquals("2+2=5",bookDtos.getContent().get(1).getTitle());
    }

    @Test
    public void checkBooksByNonExistingCategory(){
        Page<BookDto> bookDtos=bookService.getAllBooks(0,2,"science");
        assertEquals(0,bookDtos.getTotalElements());
        assertTrue(bookDtos.getContent().isEmpty());
    }



    @Test
    public void checkBooksByBlankCategory(){
        Page<BookDto> bookDtos=bookService.getAllBooks(0,2,"");
        assertEquals(4,bookDtos.getTotalElements());
        assertEquals(2,bookDtos.getSize());
    }



//checkWithId
    //checkIfNotExist
    //checkThrowBookNotFound

















    @AfterEach
    public void cleanDatabase() {
        jdbc.execute(deleteBook);
    }




















}
