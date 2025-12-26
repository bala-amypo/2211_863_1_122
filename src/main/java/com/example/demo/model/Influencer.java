package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Influencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String socialHandle;
    private Boolean active = true;

    // MANDATORY FOR TESTS: Explicitly define isActive()
    public boolean isActive() {
        return this.active != null && this.active;
    }
}