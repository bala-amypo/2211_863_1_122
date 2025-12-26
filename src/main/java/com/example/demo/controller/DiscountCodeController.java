package com.example.demo.controller;

import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discount-codes")
public class DiscountCodeController {

    private final DiscountCodeService service;

    public DiscountCodeController(DiscountCodeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiscountCode> createDiscountCode(@RequestBody DiscountCode code) {
        return ResponseEntity.ok(service.createDiscountCode(code));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountCode> getDiscountCode(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDiscountCodeById(id));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<DiscountCode>> getCodesForInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(service.getCodesForInfluencer(influencerId));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<DiscountCode>> getCodesForCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(service.getCodesForCampaign(campaignId));
    }
}