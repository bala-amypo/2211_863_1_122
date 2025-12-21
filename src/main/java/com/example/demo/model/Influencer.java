package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "influencers")
public class Influencer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String socialHandle; // Used by getSocialHandle() [cite: 689, 1153]
    private String email;
    private Boolean active = true; // Default to true [cite: 690, 1167]
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = new Timestamp(System.currentTimeMillis()); } [cite: 1168]

    // Standard Getters and Setters
    public Long getId() { return id; }
    public String getSocialHandle() { return socialHandle; }
    public void setSocialHandle(String socialHandle) { this.socialHandle = socialHandle; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    // ... add other getters/setters for name, email, createdAt
}