package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class SaleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saleAmount;
    private LocalDateTime transactionDate;

    @ManyToOne
    private DiscountCode discountCode;

    // Test Symbol Sync
    public void setTransactionAmount(BigDecimal amount) { this.saleAmount = amount; }
    public BigDecimal getTransactionAmount() { return this.saleAmount; }
}