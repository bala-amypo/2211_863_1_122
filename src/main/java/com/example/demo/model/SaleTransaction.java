package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal budget;

    // Fixes "incompatible types: int cannot be converted to BigDecimal"
    public void setBudget(Object budget) {
        if (budget instanceof Integer) {package com.example.demo.model;

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
    
    // Fixes "cannot find symbol: method setCustomerId(long)"
    private Long customerId;

    @ManyToOne
    private DiscountCode discountCode;

    // Fixes "incompatible types: java.sql.Timestamp cannot be converted to LocalDateTime"
    public void setTransactionDate(Object date) {
        if (date instanceof java.sql.Timestamp) {
            this.transactionDate = ((java.sql.Timestamp) date).toLocalDateTime();
        } else if (date instanceof LocalDateTime) {
            this.transactionDate = (LocalDateTime) date;
        }
    }

    // Fixes "incompatible types: double cannot be converted to BigDecimal"
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
            this.budget = BigDecimal.valueOf((Integer) budget);
        } else if (budget instanceof Double) {
            this.budget = BigDecimal.valueOf((Double) budget);
        } else if (budget instanceof BigDecimal) {
            this.budget = (BigDecimal) budget;
        }
    }
}