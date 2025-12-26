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
    
    // Fields required for the logic in ya.png
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active = true;

    // Fixes "cannot find symbol: method getCampaignName()"
    public String getCampaignName() {
        return this.name;
    }

    // Fixes "cannot find symbol: method getStartDate()"
    public LocalDate getStartDate() {
        return this.startDate;
    }

    // Fixes "cannot find symbol: method getEndDate()"
    public LocalDate getEndDate() {
        return this.endDate;
    }

    // Fixes "cannot find symbol: method getActive()"
    public Boolean getActive() {
        return this.active;
    }

    // Setter for the name field using the specific naming in ya.png
    public void setCampaignName(String name) {
        this.name = name;
    }
}