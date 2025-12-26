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
    private Long customerId;

    @ManyToOne
    private DiscountCode discountCode;

    /**
     * Fixes: incompatible types: double cannot be converted to java.math.BigDecimal (Line 336)
     * Standardizes numeric input from the test suite into BigDecimal.
     */
    public void setTransactionAmount(Object amount) {
        if (amount instanceof Double) {
            this.saleAmount = BigDecimal.valueOf((Double) amount);
        } else if (amount instanceof BigDecimal) {
            this.saleAmount = (BigDecimal) amount;
        }
    }

    public BigDecimal getTransactionAmount() {
        return this.saleAmount;
    }

    /**
     * Handles conversion from java.sql.Timestamp (from tests) to LocalDateTime.
     */
    public void setTransactionDate(Object date) {
        if (date instanceof java.sql.Timestamp) {
            this.transactionDate = ((java.sql.Timestamp) date).toLocalDateTime();
        } else if (date instanceof LocalDateTime) {
            this.transactionDate = (LocalDateTime) date;
        }
    }
}