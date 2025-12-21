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

    // Use constructor injection as preferred by Spring and required for testing
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        // Validation: Campaign name must be unique
        if (campaignRepository.findByCampaignName(campaign.getCampaignName()).isPresent()) {
            throw new RuntimeException("Campaign name must be unique");
        }

        // Validation: Budget must be non-negative
        if (campaign.getBudget() == null || campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        // Validation: Start date must be before end date
        if (campaign.getStartDate() != null && campaign.getEndDate() != null) {
            if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign campaign = getCampaignById(id);

        // Update fields
        campaign.setCampaignName(campaignDetails.getCampaignName());
        campaign.setBudget(campaignDetails.getBudget());
        campaign.setStartDate(campaignDetails.getStartDate());
        campaign.setEndDate(campaignDetails.getEndDate());

        // Re-validate business rules on update
        if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }
        if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign getCampaignById(Long id) {
        // Use custom exception to return 404 status
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