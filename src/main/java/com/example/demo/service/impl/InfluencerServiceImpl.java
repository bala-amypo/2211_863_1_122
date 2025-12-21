package com.example.demo.service.impl;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {
    private final InfluencerRepository repository;

    // Constructor Injection [cite: 1144]
    public InfluencerServiceImpl(InfluencerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        if (repository.findBySocialHandle(influencer.getSocialHandle()).isPresent()) {
            throw new RuntimeException("Duplicate social handle"); // [cite: 699, 1140]
        }
        return repository.save(influencer);
    }

    @Override
    public Influencer getInfluencerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found")); // [cite: 700]
    }

    @Override
    public List<Influencer> getAllInfluencers() {
        return repository.findAll();
    }
}