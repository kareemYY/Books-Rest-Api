package com.luv2code.books.repository;

import com.luv2code.books.entity.Book;
import com.luv2code.books.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    Page<Book> findByCategory(String category, Pageable pageable);
    void deleteByTitle(String title);

    List<Book> findByOwner(User user);

}
