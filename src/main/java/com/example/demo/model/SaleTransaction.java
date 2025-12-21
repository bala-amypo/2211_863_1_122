package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "sale_transactions")
public class SaleTransaction {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal saleAmount;

    private Timestamp transactionDate;

    @ManyToOne
    @JoinColumn(name = "discount_code_id", nullable = false)
    private DiscountCode discountCode;

    @PrePersist
    protected void onCreate() {
        if (this.transactionDate == null) {
            this.transactionDate = new Timestamp(System.currentTimeMillis());
        }
    }

    // Getters and Setters...
}