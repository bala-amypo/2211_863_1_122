package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository repository;
    public CampaignServiceImpl(CampaignRepository repository) { this.repository = repository; }

    @Override
    public Campaign updateCampaign(Long id, Campaign details) {
        Campaign campaign = repository.findById(id).orElseThrow();
        // Uses the custom getters to resolve ya.png symbols
        campaign.setCampaignName(details.getCampaignName());
        campaign.setStartDate(details.getStartDate());
        campaign.setEndDate(details.getEndDate());
        campaign.setActive(details.getActive());
        campaign.setBudget(details.getBudget());
        return repository.save(campaign);
    }
}