package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateRoiForCode(Long codeId);
    RoiReport getRoiReportById(Long id);
    List<RoiReport> getReportsByInfluencer(Long influencerId);
    List<RoiReport> getReportsByCampaign(Long campaignId);
}