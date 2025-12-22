package com.example.demo.service;

import com.example.demo.model.Campaign;
import java.util.List;

public interface CampaignService {
    Campaign createCampaign(Campaign campaign);
    Campaign updateCampaign(Long id, Campaign campaignDetails);
    Campaign getCampaignById(Long id);
    List<Campaign> getAllCampaigns();
    void deleteCampaign(Long id);
}