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
    private Long customerId; // Fixes symbols in 1g.png

    @ManyToOne
    private DiscountCode discountCode;

    // Fixes "Timestamp cannot be converted to LocalDateTime"
    public void setTransactionDate(Object date) {
        if (date instanceof java.sql.Timestamp) {
            this.transactionDate = ((java.sql.Timestamp) date).toLocalDateTime();
        } else if (date instanceof LocalDateTime) {
            this.transactionDate = (LocalDateTime) date;
        }
    }

    // Fixes "double cannot be converted to BigDecimal"
    public void setTransactionAmount(Object amount) {
        if (amount instanceof Double) {
            this.saleAmount = BigDecimal.valueOf((Double) amount);
        } else if (amount instanceof BigDecimal) {
            this.saleAmount = (BigDecimal) amount;
        }
    }

    public BigDecimal getTransactionAmount() { return this.saleAmount; }
}