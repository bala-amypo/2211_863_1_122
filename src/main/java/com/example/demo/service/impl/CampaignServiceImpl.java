package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        // Uses getCampaignName() to match symbol on line 25
        if (campaign.getCampaignName() == null || campaign.getCampaignName().isEmpty()) {
            throw new IllegalArgumentException("Campaign name is required");
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));

        // Synchronization with symbols from ya.png
        // Line 49: getCampaignName()
        campaign.setName(campaignDetails.getCampaignName());
        
        // Lines 35, 36, 51, 52: getStartDate() and getEndDate()
        campaign.setStartDate(campaignDetails.getStartDate());
        campaign.setEndDate(campaignDetails.getEndDate());
        
        // Line 53: getActive()
        campaign.setActive(campaignDetails.getActive());
        
        campaign.setBudget(campaignDetails.getBudget());

        return campaignRepository.save(campaign);
    }

    @Override
    public void deleteCampaign(Long id) {
        Campaign campaign = getCampaignById(id);
        campaignRepository.delete(campaign);
    }
}