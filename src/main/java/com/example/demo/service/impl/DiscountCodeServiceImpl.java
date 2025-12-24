package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Campaign;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.Influencer;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.DiscountCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final InfluencerRepository influencerRepository;
    private final CampaignRepository campaignRepository;

    // MANDATORY: Constructor Injection with specific repository order
    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository, 
                                   InfluencerRepository influencerRepository, 
                                   CampaignRepository campaignRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        // 1. Validation: Unique code check
        if (discountCodeRepository.findByCode(code.getCode()).isPresent()) {
            throw new IllegalArgumentException("Discount code already exists");
        }

        // 2. Validation: Percentage range (Required keyword: "percentage")
        if (code.getDiscountPercentage() < 0 || code.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount percentage: must be between 0 and 100");
        }

        // 3. Link Validation: Ensure Influencer and Campaign exist
        Influencer influencer = influencerRepository.findById(code.getInfluencer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
        
        Campaign campaign = campaignRepository.findById(code.getCampaign().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        code.setInfluencer(influencer);
        code.setCampaign(campaign);
        
        return discountCodeRepository.save(code);
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        code.setActive(false);
        discountCodeRepository.save(code);
    }
}