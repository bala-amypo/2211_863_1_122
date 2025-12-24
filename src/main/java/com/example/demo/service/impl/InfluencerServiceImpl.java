package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerRepository influencerRepository;

    // MANDATORY: Constructor Injection
    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        // 1. Validation: Unique Social Handle check
        // Requirement: Throw exception if handle exists
        if (influencerRepository.findBySocialHandle(influencer.getSocialHandle()).isPresent()) {
            throw new IllegalArgumentException("Influencer with this handle already exists");
        }
        
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Influencer influencer = influencerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));

        // Logic: Update fields
        influencer.setName(influencerDetails.getName());
        influencer.setEmail(influencerDetails.getEmail());
        influencer.setActive(influencerDetails.getActive());
        
        // Handle update logic (usually social handles are permanent, but if allowed):
        if (!influencer.getSocialHandle().equals(influencerDetails.getSocialHandle())) {
             if (influencerRepository.findBySocialHandle(influencerDetails.getSocialHandle()).isPresent()) {
                throw new IllegalArgumentException("New social handle is already taken");
            }
            influencer.setSocialHandle(influencerDetails.getSocialHandle());
        }

        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer getInfluencerById(Long id) {
        return influencerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
    }

    @Override
    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();
    }

    @Override
    public void deleteInfluencer(Long id) {
        if (!influencerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Influencer not found");
        }
        influencerRepository.deleteById(id);
    }
}