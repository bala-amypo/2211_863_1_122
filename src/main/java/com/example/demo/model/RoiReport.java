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
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }
public void setReportType(String reportType) { this.reportType = reportType; }
public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }
public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
public void setRoiPercentage(BigDecimal roiPercentage) { this.roiPercentage = roiPercentage; }
    private BigDecimal totalSales;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;
    
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onGenerate() { this.generatedAt = LocalDateTime.now(); }
    
    
}