package com.luv2code.books;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.books.dto.BookDto;
import com.luv2code.books.entity.Book;
import com.luv2code.books.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private ObjectMapper objectMapper=new ObjectMapper();

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

    public static final MediaType APPLICATION_JSON_UTF8=MediaType.APPLICATION_JSON;

    @BeforeAll
    public static void setup(){
        request =new MockHttpServletRequest();
        request.setParameter("title", "kk");

        request.setParameter("author", "derby");

        request.setParameter("category","math");

        request.setParameter("rating","3");
    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(createBook1);
        jdbc.execute(createBook2);
        jdbc.execute(createBook3);
        jdbc.execute(createBook4);
    }




    @Test
    public void getBooksTestHttpRequest() throws Exception {

       mockMvc.perform(get("/api/books")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalPages",is(1)))
               .andExpect(jsonPath("$.content",hasSize(4)))
               .andExpect(jsonPath("$.content[1].category",is("math")));
    }

    @Test
    public void getBookByCategoryHttpRequest() throws Exception {
        mockMvc.perform(get("/api/books").param("category","math")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",hasSize(2)))
                .andExpect(jsonPath("$.content[0].author",is("aser")))
                .andExpect(jsonPath("$.content[1].title",is("2+2=5")));
    }

    @Test
    public void getBookWithPageSize2HttpRequest() throws Exception {
        mockMvc.perform(get("/api/books").param("size","2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages",is(2)))
                .andExpect(jsonPath("$.content",hasSize(2)))
                .andExpect(jsonPath("$.content[0].author",is("kareem")));
    }

    @Test
    public void getBookWithPageSize2AndPage2HttpRequest() throws Exception {
        mockMvc.perform(get("/api/books").param("page","1").param("size","2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title",is("ramses")));
    }

    @Test
    public void getBookByIdHttpRequest() throws Exception {
        assertTrue(bookRepository.findById(2l).isPresent());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}",2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.title",is("1+1")))
                .andExpect(jsonPath("$.author",is("aser")))
                .andExpect(jsonPath("$.category",is("math")))
                .andExpect(jsonPath("$.rating",is(4)));
    }

    @Test
    public void getBookByIdWithNotFoundHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}",5))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",is("Book with id 5 not found!")))
                .andExpect(jsonPath("$.status",is(404)));
    }

    @Test
    public void createBookTestHttpRequest() throws Exception {
        Book book = new Book("WW2","Kareem","history",5);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(5)))
                .andExpect(jsonPath("$.title",is("WW2")))
                .andExpect(jsonPath("$.author",is("Kareem")))
                .andExpect(jsonPath("$.category",is("history")))
                .andExpect(jsonPath("$.rating",is(5)));

        Optional<Book> checkBook = bookRepository.findById(5l);
        assertNotNull(checkBook,"book should be fond");
        assertTrue(checkBook.isPresent());
        assertEquals("WW2",checkBook.get().getTitle());
    }

    @Test
    public void createBookWithBlankTitleHttpRequest() throws Exception {
        Book book = new Book("   ", "adam", "science", 2);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status",is(400)));
    }

    @Test
    public void updateBookTestHttpRequest() throws Exception {
        Book book = new Book("WW2","Kareem","history",5);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}",2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.title",is("WW2")))
                .andExpect(jsonPath("$.author",is("Kareem")))
                .andExpect(jsonPath("$.category",is("history")))
                .andExpect(jsonPath("$.rating" ,is(5)));
    }

    @Test
    public void updateBookWithNotFoundIdHttpRequest()throws Exception{
        Book book = new Book("WW2","Kareem","history",5);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}",6)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",is("Book with id 6 not found!")));
    }

    @Test
    public void deleteBookByIdHttpRequest() throws Exception {
            Book  book = new Book("WW2","Kareem","history",5);
            entityManager.persist(book);
            entityManager.flush();

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}",5))
                    .andExpect(status().isNoContent());
            Optional<Book> book1 = bookRepository.findById(5l);
            assertFalse(book1.isPresent());
    }

    @Test
    public void deleteBookNotFoundIdHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}",5))
                .andExpect(status().isNotFound());
        Optional<Book> book1 = bookRepository.findById(5l);
        assertFalse(book1.isPresent());

    }


    @AfterEach
    public void cleanDatabase() {
        jdbc.execute(deleteBook);
    }

}
