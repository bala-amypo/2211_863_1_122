package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class RoiReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long referenceId;
    private String reportType;
    private BigDecimal totalSales;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;

    // Setters - Required for RoiServiceImpl
    public void setReferenceId(Long id) { this.referenceId = id; }
    public void setReportType(String type) { this.reportType = type; }
    public void setTotalSales(BigDecimal sales) { this.totalSales = sales; }
    public void setTotalRevenue(BigDecimal rev) { this.totalRevenue = rev; }
    public void setRoiPercentage(BigDecimal roi) { this.roiPercentage = roi; }
}