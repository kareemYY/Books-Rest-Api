package com.luv2code.books;

import com.luv2code.books.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BooksApplicationTests {

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {

		assertEquals(6,bookService.getAllBooks().size());
	}

}
