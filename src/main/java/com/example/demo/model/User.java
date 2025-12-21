package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String role; // e.g., "ADMIN", "MARKETER"
   public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
    
}// Fixes "cannot find symbol method getEmail()"
