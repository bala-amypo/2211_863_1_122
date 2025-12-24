package com.example.demo.model;

import jakarta.persistence.*; // Change 'javax' to 'jakarta'

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String role;
    
    // Getters and Setters
}