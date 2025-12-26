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

    /**
     * Fixes: incompatible types: double cannot be converted to java.math.BigDecimal
     * Ensures transaction amounts passed as doubles are converted correctly.
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
}