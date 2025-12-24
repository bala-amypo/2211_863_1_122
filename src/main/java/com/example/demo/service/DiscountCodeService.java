package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import java.util.List;

public interface DiscountCodeService {
    DiscountCode createDiscountCode(DiscountCode code);
    List<DiscountCode> getCodesByInfluencer(Long influencerId);
    List<DiscountCode> getCodesByCampaign(Long campaignId);
    void deactivateCode(Long id);
    // ADD THIS LINE
    DiscountCode getCodeById(Long id); 
}