package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roi")
@Tag(name = "ROI Reports")
public class RoiReportController {
    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/generate/{codeId}")
    public ResponseEntity<RoiReport> generate(@PathVariable Long codeId) {
        return ResponseEntity.ok(roiService.generateRoiForCode(codeId));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<RoiReport>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(roiService.getReportsByInfluencer(influencerId));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<RoiReport>> getByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(roiService.getReportsByCampaign(campaignId));
    }
}