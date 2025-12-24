package com.example.demo.service;

import com.example.demo.model.Campaign;
import java.util.List;

/**
 * Service interface for managing marketing campaigns and their budgets.
 */
public interface CampaignService {
    
    // Create a new campaign with budget and date validation
    Campaign createCampaign(Campaign campaign);
    
    // Update existing campaign details
    Campaign updateCampaign(Long id, Campaign campaignDetails);
    
    // Retrieve a campaign by its primary key
    Campaign getCampaignById(Long id);
    
    // Retrieve all campaigns stored in the system
    List<Campaign> getAllCampaigns();
    
    // Delete a campaign by ID
    void deleteCampaign(Long id);
}