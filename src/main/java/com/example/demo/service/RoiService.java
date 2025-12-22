package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateCampaignRoi(Long campaignId);
    List<RoiReport> getReportsByCampaign(Long campaignId);
    List<RoiReport> getReportsByInfluencer(Long influencerId);
}