package com.example.demo.repository;

import com.example.demo.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    // Required for the unique social handle validation in InfluencerServiceImpl
    Optional<Influencer> findBySocialHandle(String socialHandle);
}