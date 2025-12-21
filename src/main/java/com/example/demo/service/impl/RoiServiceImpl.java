package com.example.demo.service.impl;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import com.example.demo.repository.RoiReportRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoiServiceImpl implements RoiService {
    private final RoiReportRepository roiReportRepository;

    public RoiServiceImpl(RoiReportRepository roiReportRepository) {
        this.roiReportRepository = roiReportRepository;
    }

    @Override
    public RoiReport generateCampaignRoi(Long campaignId) {
        // Implementation logic
        return new RoiReport();
    }

    // Fixes the missing method error from RoiService interface
    @Override
    public List<RoiReport> getReportsByCampaign(Long campaignId) {
        return roiReportRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public List<RoiReport> getReportsByInfluencer(Long influencerId) {
        return roiReportRepository.findByInfluencer_Id(influencerId);
    }
}