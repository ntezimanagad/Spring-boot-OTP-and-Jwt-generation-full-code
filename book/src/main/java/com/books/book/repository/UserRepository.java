package com.books.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    List<User> findAll();
}
