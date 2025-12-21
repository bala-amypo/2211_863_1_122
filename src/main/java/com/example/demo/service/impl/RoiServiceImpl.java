package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.model.RoiReport;
import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.RoiReportRepository;
import com.example.demo.repository.SaleTransactionRepository;
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

    public RoiServiceImpl(SaleTransactionRepository saleRepo, 
                          CampaignRepository campaignRepo, 
                          RoiReportRepository roiRepo) {
        this.saleRepo = saleRepo;
        this.campaignRepo = campaignRepo;
        this.roiRepo = roiRepo;
    }

    @Override
    public RoiReport generateCampaignRoi(Long campaignId) {
        Campaign campaign = campaignRepo.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        // Attribution: Fetch all sales linked to this campaign
        List<SaleTransaction> transactions = saleRepo.findByDiscountCode_Campaign_Id(campaignId);
        
        // Sum total revenue using BigDecimal
        BigDecimal totalRevenue = transactions.stream()
                .map(SaleTransaction::getSaleAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal budget = campaign.getBudget();
        BigDecimal roiPercentage = BigDecimal.ZERO;

        // ROI Formula: ((Revenue - Budget) / Budget) * 100
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