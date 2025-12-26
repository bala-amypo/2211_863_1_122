package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer discountPercentage;
    private Boolean active = true;

    @ManyToOne
    private Influencer influencer;

    @ManyToOne
    private Campaign campaign;

    // Test Symbol Sync
    public void setCodeValue(String codeValue) { this.code = codeValue; }
    public String getCodeValue() { return this.code; }
}