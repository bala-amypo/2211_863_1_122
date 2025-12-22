package com.example.demo.service.impl;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import com.example.demo.repository.RoiReportRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.model.Campaign;
import com.example.demo.model.SaleTransaction;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RoiServiceImpl implements RoiService {
    private final RoiReportRepository roiReportRepository;
    private final SaleTransactionRepository saleTransactionRepository;
    private final CampaignRepository campaignRepository;

    public RoiServiceImpl(RoiReportRepository roiReportRepository, 
                          SaleTransactionRepository saleTransactionRepository,
                          CampaignRepository campaignRepository) {
        this.roiReportRepository = roiReportRepository;
        this.saleTransactionRepository = saleTransactionRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public RoiReport generateCampaignRoi(Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        List<SaleTransaction> transactions = saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
        
        BigDecimal totalRevenue = transactions.stream()
                .map(SaleTransaction::getSaleAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal roiPercentage = BigDecimal.ZERO;
        if (campaign.getBudget().compareTo(BigDecimal.ZERO) > 0) {
            roiPercentage = totalRevenue.subtract(campaign.getBudget())
                    .divide(campaign.getBudget(), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
        }

        RoiReport report = new RoiReport();
        report.setReferenceId(campaignId);
        report.setReportType("CAMPAIGN");
        report.setTotalSales(new BigDecimal(transactions.size()));
        report.setTotalRevenue(totalRevenue);
        report.setRoiPercentage(roiPercentage);

        return roiReportRepository.save(report);
    }

    @Override
    public List<RoiReport> getReportsByCampaign(Long campaignId) {
        return roiReportRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public List<RoiReport> getReportsByInfluencer(Long influencerId) {
        return roiReportRepository.findByInfluencer_Id(influencerId);
    }
}