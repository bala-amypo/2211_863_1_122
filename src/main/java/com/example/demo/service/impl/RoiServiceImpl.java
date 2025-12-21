package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RoiService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RoiServiceImpl implements RoiService {

    private final SaleTransactionRepository saleRepo;
    private final CampaignRepository campaignRepo;
    private final RoiReportRepository roiRepo;

    public RoiServiceImpl(SaleTransactionRepository saleRepo, CampaignRepository campaignRepo, RoiReportRepository roiRepo) {
        this.saleRepo = saleRepo;
        this.campaignRepo = campaignRepo;
        this.roiRepo = roiRepo;
    }

    @Override
    public RoiReport generateCampaignRoi(Long campaignId) {
        Campaign campaign = campaignRepo.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        // Use the exact repository method name required for attribution
        List<SaleTransaction> transactions = saleRepo.findByDiscountCode_Campaign_Id(campaignId);
        
        // Sum up total revenue
        BigDecimal totalRevenue = transactions.stream()
                .map(SaleTransaction::getSaleAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal budget = campaign.getBudget();
        BigDecimal roiPercentage = BigDecimal.ZERO;

        // ROI Calculation: ((Revenue - Budget) / Budget) * 100
        if (budget.compareTo(BigDecimal.ZERO) > 0) {
            roiPercentage = totalRevenue.subtract(budget)
                    .divide(budget, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
        }

        RoiReport report = new RoiReport();
        report.setReferenceId(campaignId);
        report.setReportType("CAMPAIGN");
        report.setTotalSales(new BigDecimal(transactions.size()));
        report.setTotalRevenue(totalRevenue);
        report.setRoiPercentage(roiPercentage);

        return roiRepo.save(report);
    }
}