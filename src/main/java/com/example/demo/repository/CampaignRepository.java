package com.example.demo.repository;

import com.example.demo.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    /**
     * Finds a campaign by its unique name.
     * Required for validating that no two campaigns share the same name 
     * during the creation process in CampaignServiceImpl.
     */
    Optional<Campaign> findByCampaignName(String campaignName);

    /**
     * Retrieves campaigns that are currently active based on dates.
     * Useful for the ROI service to filter relevant performance data.
     */
    List<Campaign> findByEndDateAfter(LocalDate date);

    /**
     * Finds campaigns with a budget greater than or equal to a specific amount.
     */
    List<Campaign> findByBudgetGreaterThanEqual(java.math.BigDecimal amount);
}