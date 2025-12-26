package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import java.util.List;

public interface DiscountCodeService {
    DiscountCode createDiscountCode(DiscountCode code);
    List<DiscountCode> getCodesForInfluencer(Long influencerId);
    List<DiscountCode> getCodesForCampaign(Long campaignId);
    DiscountCode getDiscountCodeById(Long id); // Sync symbol
    void deactivateCode(Long id);
}