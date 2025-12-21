package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "sale_transactions")
public class SaleTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    private BigDecimal saleAmount; // Used by getSaleAmount() [cite: 1160, 1183]
    private Timestamp transactionDate; // Used by getTransactionDate() [cite: 1183]

    @PrePersist
    protected void onCreate() { 
        if (this.transactionDate == null) this.transactionDate = new Timestamp(System.currentTimeMillis()); 
    } [cite: 758, 1186]

    // Standard Getters and Setters
    public BigDecimal getSaleAmount() { return saleAmount; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
    public DiscountCode getDiscountCode() { return discountCode; }
    // ... add other getters/setters
}