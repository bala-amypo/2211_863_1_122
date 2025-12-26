package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateRoiForCode(Long codeId);
    List<RoiReport> getReportsForInfluencer(Long influencerId);
    List<RoiReport> getReportsForCampaign(Long campaignId);
    RoiReport getReportById(Long id);
}