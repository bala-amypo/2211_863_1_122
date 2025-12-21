package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    // Required to fix "cannot find symbol getEmail()" in UserServiceImpl
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}