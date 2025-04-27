package com.books.book.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String otpCode;
    private Instant createdAt;
    private Instant expiredAt;
    private String purpose;
    public Otp() {
    }
    public Otp(String username, String otpCode, Instant createdAt, Instant expiredAt, String purpose) {
        this.username = username;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.purpose = purpose;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOtpCode() {
        return otpCode;
    }
    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getExpiredAt() {
        return expiredAt;
    }
    public void setExpiredAt(Instant expiredAt) {
        this.expiredAt = expiredAt;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
}
