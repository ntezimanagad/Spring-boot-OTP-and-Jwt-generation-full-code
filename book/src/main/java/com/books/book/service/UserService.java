package com.books.book.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.books.book.dto.UserDTO;
import com.books.book.jwt.JwtUtil;
import com.books.book.model.Blacklist;
import com.books.book.model.Role;
import com.books.book.model.User;
import com.books.book.repository.TokenBlacklist;
import com.books.book.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpService otpService;
    @Autowired
    private TokenBlacklist tokenBlacklist;
    public List<String> getAllEmail(){
        return userRepository.findAll()
            .stream()
            .map(User::getEmail)
            .collect(Collectors.toList());
    }
    public void registerUser(UserDTO userDTO){
        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User Already Exists");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        otpService.generateAndSendOtp(userDTO.getUsername(), userDTO.getEmail(), "REGISTER");
    }
    public void validateRegisterOtp(String username, String otpCode){
        boolean valid = otpService.validateOTP(username, otpCode, "REGISTER");
        if (!valid) {
            throw new RuntimeException("Invalid Or expired token");
        }
        otpService.deleteOtp(username, "REGISTER");
    }
    public void loginUser(UserDTO userDTO){
        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("incorect password");
        }
        otpService.generateAndSendOtp(user.getUsername(), user.getEmail(), "LOGIN");
    }
    public String validateLoginOtp(String username, String otpCode){
        boolean valid = otpService.validateOTP(username, otpCode, "LOGIN");
        if (!valid) {
            throw new RuntimeException("Failled to validate Otp may be expired or wrong");
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }
        User user = optionalUser.get();
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
    public void logout(String token){
        Blacklist blacklist = new Blacklist(token, Instant.now());
        tokenBlacklist.save(blacklist);
    }
}
