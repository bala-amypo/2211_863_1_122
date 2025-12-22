package com.example.demo.service.impl;

import com.example.demo.model.Influencer;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.service.InfluencerService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InfluencerServiceImpl implements InfluencerService {

    private final InfluencerRepository influencerRepository;

    public InfluencerServiceImpl(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Override
    public Influencer createInfluencer(Influencer influencer) {
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
    public Influencer updateInfluencer(Long id, Influencer details) {
        Influencer influencer = getInfluencerById(id);
        influencer.setName(details.getName());
        influencer.setSocialHandle(details.getSocialHandle());
        influencer.setEmail(details.getEmail());
        influencer.setActive(details.getActive());
        return influencerRepository.save(influencer);
    }

    @Override
    public void deleteInfluencer(Long id) {
        Influencer influencer = getInfluencerById(id);
        influencerRepository.delete(influencer);
    }
}