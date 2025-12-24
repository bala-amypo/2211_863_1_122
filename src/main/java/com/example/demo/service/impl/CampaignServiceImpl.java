package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    // MANDATORY: Constructor Injection
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        // 1. Validation: Unique Campaign Name
        if (campaignRepository.findByCampaignName(campaign.getCampaignName()).isPresent()) {
            throw new IllegalArgumentException("Campaign name already exists");
        }

        // 2. Validation: Budget must be non-negative (BigDecimal comparison)
        if (campaign.getBudget() == null || campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Budget must be a non-negative value");
        }

        // 3. Validation: Date range logic (Required keyword: "date")
        if (campaign.getStartDate() == null || campaign.getEndDate() == null || 
            campaign.getStartDate().isAfter(campaign.getEndDate())) {
            throw new IllegalArgumentException("Invalid campaign date range: Start date cannot be after end date");
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with ID: " + id));

        // Update logic
        campaign.setCampaignName(campaignDetails.getCampaignName());
        campaign.setBudget(campaignDetails.getBudget());
        campaign.setStartDate(campaignDetails.getStartDate());
        campaign.setEndDate(campaignDetails.getEndDate());
        campaign.setActive(campaignDetails.getActive());

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public void deleteCampaign(Long id) {
        if (!campaignRepository.existsById(id)) {
            throw new ResourceNotFoundException("Campaign not found");
        }
        campaignRepository.deleteById(id);
    }
}