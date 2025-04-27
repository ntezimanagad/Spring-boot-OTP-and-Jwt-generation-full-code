package com.books.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.model.Blacklist;

public interface TokenBlacklist extends JpaRepository<Blacklist, String>{
    boolean existsByToken(String token);
}
