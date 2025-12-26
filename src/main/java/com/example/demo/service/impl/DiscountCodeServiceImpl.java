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

    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository, 
                                   InfluencerRepository influencerRepository, 
                                   CampaignRepository campaignRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        // Sync with Model: Using getCodeValue()
        if (discountCodeRepository.findByCode(code.getCodeValue()).isPresent()) {
            throw new IllegalArgumentException("Discount code already exists");
        }

        if (code.getDiscountPercentage() < 0 || code.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount percentage: must be between 0 and 100");
        }

        Influencer influencer = influencerRepository.findById(code.getInfluencer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
        
        Campaign campaign = campaignRepository.findById(code.getCampaign().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        code.setInfluencer(influencer);
        code.setCampaign(campaign);
        
        return discountCodeRepository.save(code);
    }

    @Override
    public List<DiscountCode> getCodesForInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesForCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public DiscountCode getDiscountCodeById(Long id) {
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
    // Inside DiscountCodeServiceImpl.java
@Override
public DiscountCode updateDiscountCode(Long id, DiscountCode updatedCode) {
    DiscountCode existing = discountCodeRepository.findById(id)
            .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Discount code not found"));
    
    if (updatedCode.getCodeValue() != null) {
        existing.setCode(updatedCode.getCodeValue());
    }
    if (updatedCode.getDiscountPercentage() != null) {
        existing.setDiscountPercentage(updatedCode.getDiscountPercentage());
    }
    
    return discountCodeRepository.save(existing);
}
}