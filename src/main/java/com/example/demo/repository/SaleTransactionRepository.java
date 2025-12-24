package com.example.demo.repository;

import com.example.demo.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
    // Finds all sales linked to a specific campaign via discount codes
    List<SaleTransaction> findByDiscountCode_Campaign_Id(Long campaignId);
}