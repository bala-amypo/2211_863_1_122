package com.example.demo.repository;

import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

   
    Optional<DiscountCode> findByCode(String code);

   
    List<DiscountCode> findByInfluencer_Id(Long influencerId);

   
    List<DiscountCode> findByCampaign_Id(Long campaignId);
    
   
    List<DiscountCode> findByCampaign_IdAndActiveTrue(Long campaignId);
}