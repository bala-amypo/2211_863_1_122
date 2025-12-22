package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roi")
public class RoiReportController {
    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/generate/campaign/{id}")
    public ResponseEntity<RoiReport> generateReport(@PathVariable Long id) {
        return ResponseEntity.ok(roiService.generateCampaignRoi(id));
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<List<RoiReport>> getCampaignReports(@PathVariable Long id) {
        return ResponseEntity.ok(roiService.getReportsByCampaign(id));
    }
}