package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
    // Use the exact naming required by Step 0 [cite: 1158]
    List<RoiReport> findByCampaign_Id(Long campaignId);
    
    // For influencer reports, use the relationship mapping
    List<RoiReport> findByInfluencer_Id(Long influencerId);
}