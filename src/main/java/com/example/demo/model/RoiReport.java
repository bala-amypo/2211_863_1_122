package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "roi_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoiReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalSales;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalRevenue;

    @Column(precision = 19, scale = 4)
    private BigDecimal roiPercentage;

    private Integer totalTransactions;

    private LocalDateTime generatedAt;

    @PrePersist
    protected void onGenerate() {
        this.generatedAt = LocalDateTime.now();
    }
}