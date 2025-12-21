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

    // Constructor injection as per requirements
    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
        // Business Rule: Social handle must be unique
        if (influencerRepository.findBySocialHandle(influencer.getSocialHandle()).isPresent()) {
            throw new RuntimeException("Duplicate social handle");
        }
        return influencerRepository.save(influencer);
    }

    @Override
    public Influencer updateInfluencer(Long id, Influencer influencerDetails) {
        Influencer influencer = getInfluencerById(id);
        influencer.setName(influencerDetails.getName());
        influencer.setSocialHandle(influencerDetails.getSocialHandle());
        influencer.setEmail(influencerDetails.getEmail());
        influencer.setActive(influencerDetails.getActive());
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