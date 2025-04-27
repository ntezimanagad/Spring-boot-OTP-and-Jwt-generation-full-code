package com.books.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long>{
    Optional<Otp> findByUsernameAndPurpose(String username, String purpose);
}
