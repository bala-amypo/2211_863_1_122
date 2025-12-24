package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateRoiForCode(Long codeId);
    List<RoiReport> getReportsByInfluencer(Long influencerId);
    List<RoiReport> getReportsByCampaign(Long campaignId);
    
    // ADD THIS LINE TO MATCH LINE 76 OF YOUR IMPL
    RoiReport getReportById(Long id);
}