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
    
    // Fixes "cannot find symbol: method setCustomerId(long)" from 1q.png/2q.png
    private Long customerId;

    @ManyToOne
    private DiscountCode discountCode;

    /**
     * Fixes: "illegal start of expression" in 1b.png
     * Fixes: "java.sql.Timestamp cannot be converted to LocalDateTime" in 1q.png/1g.png
     */
    public void setTransactionDate(Object date) {
        if (date instanceof java.sql.Timestamp) {
            this.transactionDate = ((java.sql.Timestamp) date).toLocalDateTime();
        } else if (date instanceof LocalDateTime) {
            this.transactionDate = (LocalDateTime) date;
        }
    }

    /**
     * Fixes: "not a statement" in 1b.png
     * Fixes: "double cannot be converted to BigDecimal" in 1g.png/2q.png
     */
    public void setTransactionAmount(Object amount) {
        if (amount instanceof Double) {
            this.saleAmount = BigDecimal.valueOf((Double) amount);
        } else if (amount instanceof BigDecimal) {
            this.saleAmount = (BigDecimal) amount;
        }
    }

    /**
     * Fixes: "cannot find symbol: method getTransactionAmount()" 
     * Required for ROI calculations and test assertions.
     */
    public BigDecimal getTransactionAmount() {
        return this.saleAmount;
    }

    // Explicit getters/setters for customerId to ensure symbol resolution
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }
}