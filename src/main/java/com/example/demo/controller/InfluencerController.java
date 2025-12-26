package com.example.demo.controller;

import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/influencers")
public class InfluencerController {

    private final InfluencerService service;

    public InfluencerController(InfluencerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Influencer> createInfluencer(@RequestBody Influencer influencer) {
        return ResponseEntity.ok(service.createInfluencer(influencer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInfluencerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
        return ResponseEntity.ok(service.getAllInfluencers());
    }
}