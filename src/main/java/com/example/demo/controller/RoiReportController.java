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
public ResponseEntity<List<RoiReport>> getReportsByInfluencer(@PathVariable Long influencerId) {
    // Change method call below:
    return ResponseEntity.ok(roiService.getReportsForInfluencer(influencerId));
}

@GetMapping("/campaign/{campaignId}")
public ResponseEntity<List<RoiReport>> getReportsByCampaign(@PathVariable Long campaignId) {
    // Change method call below:
    return ResponseEntity.ok(roiService.getReportsForCampaign(campaignId));
}
}