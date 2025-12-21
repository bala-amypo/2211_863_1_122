package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    // Constructor injection as required by the technical constraints
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        // 1. Validate Campaign Name Uniqueness
        if (campaignRepository.findByCampaignName(campaign.getCampaignName()).isPresent()) {
            throw new RuntimeException("Campaign name must be unique");
        }

        // 2. Validate Budget (Must be non-negative BigDecimal)
        if (campaign.getBudget() == null || campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        // 3. Validate Date Range (Start Date must be before End Date)
        if (campaign.getStartDate() != null && campaign.getEndDate() != null) {
            if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        } else {
            throw new RuntimeException("Campaign must have both start and end dates");
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign existingCampaign = getCampaignById(id);

        // Update fields and check for symbols
        existingCampaign.setCampaignName(campaignDetails.getCampaignName());
        existingCampaign.setBudget(campaignDetails.getBudget());
        existingCampaign.setStartDate(campaignDetails.getStartDate());
        existingCampaign.setEndDate(campaignDetails.getEndDate());

        // Re-run critical validations
        if (existingCampaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }
        if (existingCampaign.getStartDate().isAfter(existingCampaign.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        return campaignRepository.save(existingCampaign);
    }
}