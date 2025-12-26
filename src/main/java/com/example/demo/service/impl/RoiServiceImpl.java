package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RoiService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RoiServiceImpl implements RoiService {

    private final RoiReportRepository roiReportRepository;
    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    public RoiServiceImpl(RoiReportRepository roiReportRepository, 
                          SaleTransactionRepository saleTransactionRepository, 
                          DiscountCodeRepository discountCodeRepository) {
        this.roiReportRepository = roiReportRepository;
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public RoiReport generateRoiForCode(Long codeId) {
        DiscountCode code = discountCodeRepository.findById(codeId)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found with ID: " + codeId));

        List<SaleTransaction> transactions = saleTransactionRepository.findByDiscountCode_Id(codeId);

        // Sync with Model: Using getTransactionAmount()
        BigDecimal totalSales = transactions.stream()
                .map(SaleTransaction::getTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double discountFactor = 1 - (code.getDiscountPercentage() / 100.0);
        BigDecimal totalRevenue = totalSales.multiply(BigDecimal.valueOf(discountFactor))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal campaignBudget = code.getCampaign().getBudget();
        BigDecimal roiPercentage = BigDecimal.ZERO;

        if (campaignBudget.compareTo(BigDecimal.ZERO) > 0) {
            roiPercentage = totalRevenue.subtract(campaignBudget)
                    .divide(campaignBudget, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        RoiReport report = new RoiReport();
        report.setDiscountCode(code);
        report.setCampaign(code.getCampaign());
        report.setInfluencer(code.getInfluencer());
        report.setTotalSales(totalSales);
        report.setTotalRevenue(totalRevenue);
        report.setRoiPercentage(roiPercentage);
        report.setTotalTransactions(transactions.size());

        return roiReportRepository.save(report);
    }

    @Override
    public List<RoiReport> getReportsForInfluencer(Long influencerId) {
        return roiReportRepository.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<RoiReport> getReportsForCampaign(Long campaignId) {
        return roiReportRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public RoiReport getReportById(Long id) {
        return roiReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ROI Report not found"));
    }
}