package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for User Registration and Login")
public class AuthController {

    private final UserService userService;

    // MANDATORY: Constructor Injection
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new User entity and returns the saved object.")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        // Logic handled in UserServiceImpl: checks for duplicate email and hashes password
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Verifies credentials and returns a JWT Bearer token.")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Logic handled in UserServiceImpl: uses AuthenticationManager and JwtUtil
        return ResponseEntity.ok(userService.login(request));
    }
}