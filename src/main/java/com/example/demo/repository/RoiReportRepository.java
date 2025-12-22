package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
   
    List<RoiReport> findByCampaign_Id(Long campaignId);
    
   
    List<RoiReport> findByInfluencer_Id(Long influencerId);
}