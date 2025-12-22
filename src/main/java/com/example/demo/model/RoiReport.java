package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "roi_reports")
public class RoiReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long referenceId;
    private String reportType;
    private BigDecimal totalSales;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;

    
    public void setReferenceId(Long id) { this.referenceId = id; }
    public void setReportType(String type) { this.reportType = type; }
    public void setTotalSales(BigDecimal sales) { this.totalSales = sales; }
    public void setTotalRevenue(BigDecimal revenue) { this.totalRevenue = revenue; }
    public void setRoiPercentage(BigDecimal roi) { this.roiPercentage = roi; }
   
}