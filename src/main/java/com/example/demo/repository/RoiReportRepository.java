package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
    // Keep ONLY one instance of this method
    Optional<RoiReport> findByReferenceIdAndReportType(Long referenceId, String reportType);
    
    List<RoiReport> findByCampaign_Id(Long campaignId);
    List<RoiReport> findByInfluencer_Id(Long influencerId);
}