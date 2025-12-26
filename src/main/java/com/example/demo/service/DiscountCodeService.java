package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import java.util.List;

public interface DiscountCodeService {
    DiscountCode createDiscountCode(DiscountCode code);
    List<DiscountCode> getCodesForInfluencer(Long influencerId);
    List<DiscountCode> getCodesForCampaign(Long campaignId);
    DiscountCode getDiscountCodeById(Long id);
    void deactivateCode(Long id);
    
    // Add this to fix Line 166 errors
    DiscountCode updateDiscountCode(Long id, DiscountCode code);
}