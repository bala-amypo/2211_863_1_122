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
        if (budget instanceof Integer) {
            this.budget = BigDecimal.valueOf((Integer) budget);
        } else if (budget instanceof Double) {
            this.budget = BigDecimal.valueOf((Double) budget);
        } else if (budget instanceof BigDecimal) {
            this.budget = (BigDecimal) budget;
        }
    }
}