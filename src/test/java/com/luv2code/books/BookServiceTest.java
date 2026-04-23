package com.luv2code.books;


import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.exception.BookNotFoundException;
import com.luv2code.books.repository.BookRepository;
import com.luv2code.books.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(BookServiceTest.class);
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


    @Test
    public void checkBookById(){
        BookDto book = bookService.getBookById(2);

        assertNotNull(book);
        assertEquals(2,book.getId());
        assertEquals("math",book.getCategory());
        assertEquals("aser",book.getAuthor());
        assertEquals(4,book.getRating());
        assertEquals("1+1" ,book.getTitle());
    }

    @Test
    public void checkBookByIdNotFound(){
        assertThrows(BookNotFoundException.class, () ->  bookService.getBookById(11));
    }

    @Test
    public void createBookAndWithHappyPath(){
        BookDto bookDto = new BookDto("book2","kareem","history",1);
        BookDto savedBook = bookService.createBook(bookDto);
        assertNotNull(savedBook);
        assertNotEquals(0,savedBook.getId());
        assertEquals("kareem",savedBook.getAuthor());
    }

    @Test
    public void updateBookAndWithHappyPath(){
        BookDto bookDto = new BookDto("book2","kareem","history",1);
        BookDto updatedBookDto= bookService.updateBook(3,bookDto);
        assertNotNull(updatedBookDto);
        assertNotEquals(0,updatedBookDto.getId());
        assertEquals("kareem",updatedBookDto.getAuthor());
        assertEquals("book2",updatedBookDto.getTitle());
        assertEquals(1,updatedBookDto.getRating());
    }

    @Test
    public void updateBookWithNotFoundId(){
        assertEquals(4, bookRepo.count());
        BookDto bookDto = new BookDto("book2","kareem","history",1);
        assertThrows(BookNotFoundException.class, () ->  bookService.updateBook(9,bookDto));
        assertFalse(bookService.checkIfBookExistsByTitle("book2"));
        assertEquals(4, bookRepo.count());
    }

    @Test
    public void deleteBookAndWithHappyPath(){
        assertEquals(4, bookRepo.count());
        long id =2 ;
        BookDto bookDto = bookService.getBookById(id);
        assertEquals("aser",bookDto.getAuthor());
        assertEquals(4,bookDto.getRating());
        assertEquals("1+1" ,bookDto.getTitle());
        assertEquals("math",bookDto.getCategory());

        bookService.deleteBook(id);
        assertEquals(3,bookRepo.count());
        assertThrows(BookNotFoundException.class, () ->  bookService.getBookById(id));
    }


    @Test
    public void deleteBookWithNotFoundId(){
        assertEquals(4, bookRepo.count());
        long id =20;
        assertThrows(BookNotFoundException.class, () ->  bookService.deleteBook(id));
        assertEquals(4, bookRepo.count());
    }











    @AfterEach
    public void cleanDatabase() {
        jdbc.execute(deleteBook);
    }




















}
