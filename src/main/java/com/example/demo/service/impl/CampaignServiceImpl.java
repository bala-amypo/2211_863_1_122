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

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        // Unique name validation
        if (campaignRepository.findByCampaignName(campaign.getCampaignName()).isPresent()) {
            throw new RuntimeException("Campaign name must be unique");
        }

        // Budget validation
        if (campaign.getBudget() == null || campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        // Date range validation
        if (campaign.getStartDate() != null && campaign.getEndDate() != null) {
            if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        }

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
    public Campaign updateCampaign(Long id, Campaign details) {
        Campaign campaign = getCampaignById(id);
        
        campaign.setCampaignName(details.getCampaignName());
        campaign.setBudget(details.getBudget());
        campaign.setStartDate(details.getStartDate());
        campaign.setEndDate(details.getEndDate());

        return campaignRepository.save(campaign);
    }
}