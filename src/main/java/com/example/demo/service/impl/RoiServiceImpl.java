package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "roi_reports")
public class RoiReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long referenceId; // Can be Campaign ID or Influencer ID
    private String reportType; // "CAMPAIGN" or "INFLUENCER"
    
    private BigDecimal totalSales;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;
    
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onGenerate() { this.generatedAt = LocalDateTime.now(); }
    
    // Getters and Setters
}