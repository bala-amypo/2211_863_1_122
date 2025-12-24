package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoiReportDTO {
    private Long id;
    private String campaignName;
    private String influencerHandle;
    private String discountCode;
    private BigDecimal totalSales;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;
    private Integer totalTransactions;
    private LocalDateTime generatedAt;
}