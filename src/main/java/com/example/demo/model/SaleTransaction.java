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
    private Timestamp transactionDate;

    @PrePersist
    protected void onCreate() { 
        if (this.transactionDate == null) this.transactionDate = new Timestamp(System.currentTimeMillis()); 
    } // [cite: 909, 1186]

    public BigDecimal getSaleAmount() { return saleAmount; }
    public void setSaleAmount(BigDecimal saleAmount) { this.saleAmount = saleAmount; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
    public DiscountCode getDiscountCode() { return discountCode; }
    public void setDiscountCode(DiscountCode discountCode) { this.discountCode = discountCode; }
}