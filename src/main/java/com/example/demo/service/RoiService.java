package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateRoiForCode(Long codeId);
    // ENSURE THESE TWO LINES ARE PRESENT
    List<RoiReport> getReportsByInfluencer(Long influencerId);
    List<RoiReport> getReportsByCampaign(Long campaignId);
}