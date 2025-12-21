package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {

    /**
     * Retrieves all ROI reports generated for a specific influencer.
     * Maps to the GET /api/roi/influencer/{influencerId} endpoint.
     */
    List<RoiReport> findByReferenceIdAndReportType(Long influencerId, String reportType);

    /**
     * Retrieves all ROI reports generated for a specific campaign.
     * Maps to the GET /api/roi/campaign/{campaignId} endpoint.
     */
    List<RoiReport> findByReferenceIdAndReportType(Long campaignId, String reportType);

    /**
     * Custom finder to get reports based on the type (e.g., "CAMPAIGN" or "INFLUENCER").
     */
    List<RoiReport> findByReportType(String reportType);
}