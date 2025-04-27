📚 Spring Boot Backend - OTP Verification & JWT Authentication
This is a Spring Boot backend project that implements OTP (One-Time Password) verification during user registration and JWT (JSON Web Token) generation for secure authentication and authorization.

🚀 Features
✅ User Registration

Generate and send OTP to users

Verify OTP for account activation

✅ User Authentication

Login with username and password

Issue JWT Token upon successful login

✅ Secure Endpoints

Role-based access control using JWT

Only authenticated users can access protected resources

✅ Custom Exception Handling

Clean and centralized error responses

Handles common issues like invalid OTP, expired token, unauthorized access

✅ DTOs and Mappers

Uses Data Transfer Objects (DTO) for cleaner API structure

Maps between Entities and DTOs using custom Mapper classes

🛠 Tech Stack
Backend: Java 17, Spring Boot

Security: Spring Security + JWT

Database: MySQL (via Spring Data JPA)

Build Tool: Maven
