package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Campaign {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String campaignName;
    private BigDecimal budget;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters and Setters - Required to fix "cannot find symbol"
    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String name) { this.campaignName = name; }
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate date) { this.startDate = date; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate date) { this.endDate = date; }
}