package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // MANDATORY: Constructor Injection for all dependencies
    public UserServiceImpl(UserRepository userRepository, 
                          PasswordEncoder passwordEncoder, 
                          JwtUtil jwtUtil, 
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User register(RegisterRequest request) {
        // Validation: Email must be unique
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        
        // Logic: Hash the password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Logic: Default role to MARKETER if not provided
        if (request.getRole() == null || request.getRole().isEmpty()) {
            user.setRole("MARKETER");
        } else {
            user.setRole(request.getRole());
        }

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // 1. Authenticate using Spring Security's AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Fetch user details to generate token
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3. Generate JWT Token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        // 4. Return DTO with token and basic info
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}