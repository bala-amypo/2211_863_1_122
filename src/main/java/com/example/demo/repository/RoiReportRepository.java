package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
    
    // Now valid because RoiReport has a 'campaign' field
    List<RoiReport> findByCampaign_Id(Long campaignId);
    
    // Now valid because RoiReport has an 'influencer' field
    List<RoiReport> findByInfluencer_Id(Long influencerId);
}