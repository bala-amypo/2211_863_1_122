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
    private String socialHandle; // Must be unique [cite: 689, 1166]
    private String email;
    private Boolean active = true; // Default to true [cite: 690, 1167]
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() { this.createdAt = new Timestamp(System.currentTimeMillis()); } // [cite: 1168]
    
    public String getName() { return name; }
public void setName(String name) { this.name = name; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSocialHandle() { return socialHandle; }
    public void setSocialHandle(String socialHandle) { this.socialHandle = socialHandle; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}