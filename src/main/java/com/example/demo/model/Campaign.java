package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active = true;

    // Standard getter for Name
    public String getCampaignName() {
        return this.name;
    }

    // Standard setter for Name
    public void setCampaignName(String name) {
        this.name = name;
    }

    /**
     * Fixes: incompatible types: double cannot be converted to java.lang.Integer (Line 287)
     * This overloaded setter allows the test suite to pass double values 
     * which are then safely converted to the entity's BigDecimal type.
     */
    public void setBudget(Object budget) {
        if (budget instanceof Double) {
            this.budget = BigDecimal.valueOf((Double) budget);
        } else if (budget instanceof Integer) {
            this.budget = BigDecimal.valueOf((Integer) budget);
        } else if (budget instanceof BigDecimal) {
            this.budget = (BigDecimal) budget;
        }
    }
}