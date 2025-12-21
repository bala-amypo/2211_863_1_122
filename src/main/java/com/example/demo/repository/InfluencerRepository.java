package com.example.demo.repository;

import com.example.demo.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    /**
     * Used for unique constraint validation during creation.
     * The Docx specifies social handles must be unique.
     */
    Optional<Influencer> findBySocialHandle(String socialHandle);

    /**
     * Used to verify if an email is already associated with an influencer.
     */
    Optional<Influencer> findByEmail(String email);
}