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

    // Fixes "cannot find symbol: method getCampaignName()"
    public String getCampaignName() { return this.name; }
    public void setCampaignName(String name) { this.name = name; }

    // Fixes "incompatible types: double cannot be converted to Integer/BigDecimal"
    public void setBudget(Object budget) {
        if (budget instanceof Double) {
            this.budget = BigDecimal.valueOf((Double) budget);
        } else if (budget instanceof Integer) {
            this.budget = BigDecimal.valueOf((Integer) budget);
        } else if (budget instanceof BigDecimal) {
            this.budget = (BigDecimal) budget;
        }
    }

    // Explicit getters for symbols in ya.png
    public LocalDate getStartDate() { return this.startDate; }
    public LocalDate getEndDate() { return this.endDate; }
    public Boolean getActive() { return this.active; }
}