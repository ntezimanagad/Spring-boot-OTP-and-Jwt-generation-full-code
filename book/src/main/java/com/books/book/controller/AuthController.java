package com.books.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.book.dto.UserDTO;
import com.books.book.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/sendOtp")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        userService.registerUser(userDTO);
        return ResponseEntity.ok("User created and otp to activate account setn to your email");
    }
    @PostMapping(value = "/validate")
    public ResponseEntity<?> validateRegisterOtp(@RequestParam String username, @RequestParam String otpCode){
        userService.validateRegisterOtp(username, otpCode);
        return ResponseEntity.ok("User created and otp to activate account setn to your email");
    }
    @PostMapping(value = "/sendOtp")
    public ResponseEntity<?> loginUserUser(@RequestBody UserDTO userDTO){
        userService.loginUser(userDTO);
        return ResponseEntity.ok("otp to activate account setn to your email");
    }
    @PostMapping(value = "/validateLogin")
    public ResponseEntity<?> validateLoginOtp(@RequestParam String username, @RequestParam String otpCode){
        userService.validateRegisterOtp(username, otpCode);
        return ResponseEntity.ok("login activated");
    }
    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            userService.logout(token);
        }
        return ResponseEntity.ok("Failled to log out");
    }

}
