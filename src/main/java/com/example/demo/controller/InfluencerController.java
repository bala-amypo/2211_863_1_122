package com.example.demo.controller;

import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/influencers") // [cite: 1262]
public class InfluencerController {
    private final InfluencerService influencerService;

    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    @PostMapping
    public Influencer create(@RequestBody Influencer influencer) {
        return influencerService.createInfluencer(influencer);
    }

    @GetMapping
    public List<Influencer> getAll() {
        return influencerService.getAllInfluencers();
    }

    @GetMapping("/{id}")
    public Influencer getById(@PathVariable Long id) {
        return influencerService.getInfluencerById(id);
    }
}