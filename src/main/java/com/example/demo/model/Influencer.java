package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "influencers")
public class Influencer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String socialHandle;
    private String email;
    private boolean active = true; // Default true [cite: 690, 1167]
    private Date createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = new Date(); }

    public Influencer() {}
    public Influencer(String name, String socialHandle, boolean active) {
        this.name = name;
        this.socialHandle = socialHandle;
        this.active = active;
    }
    // Getters and Setters...
}