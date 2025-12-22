package com.example.demo.repository;

import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    /**
     * Finds a discount code by the string code (e.g., "SUMMER20").
     * Necessary for unique validation during creation.
     */
    Optional<DiscountCode> findByCode(String code);

    /**
     * Lists all codes assigned to a specific influencer.
     * Required for the GET /api/codes/influencer/{influencerId} endpoint.
     */
    List<DiscountCode> findByInfluencer_Id(Long influencerId);

    /**
     * Lists all codes assigned to a specific campaign.
     * Required for the GET /api/codes/campaign/{campaignId} endpoint.
     */
    List<DiscountCode> findByCampaign_Id(Long campaignId);
    
    /**
     * Finds codes that are currently active for a specific campaign.
     */
    List<DiscountCode> findByCampaign_IdAndActiveTrue(Long campaignId);
}