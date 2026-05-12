package com.luv2code.books.repository;

import com.luv2code.books.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    @Query("select COUNT (u) FROM User u JOIN u.authorities a WHERE a.authority='ROLE_ADMIN'")
    long countAdmin();
}
