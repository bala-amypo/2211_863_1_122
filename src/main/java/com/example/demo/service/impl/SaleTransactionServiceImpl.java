package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.SaleTransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {

    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    // MANDATORY: Constructor Injection
    public SaleTransactionServiceImpl(SaleTransactionRepository saleTransactionRepository, 
                                     DiscountCodeRepository discountCodeRepository) {
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public SaleTransaction createSale(SaleTransaction transaction) {
        // 1. Validation: Sale Amount must be positive
        if (transaction.getSaleAmount() == null || transaction.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sale amount must be greater than zero");
        }

        // 2. Attribution: Verify the Discount Code exists
        DiscountCode code = discountCodeRepository.findById(transaction.getDiscountCode().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));

        // 3. Logic: Ensure the code is still active
        if (!code.getActive()) {
            throw new IllegalArgumentException("This discount code is inactive and cannot be used");
        }

        // 4. Logic: Set transaction date if not provided
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDateTime.now());
        }

        transaction.setDiscountCode(code);
        return saleTransactionRepository.save(transaction);
    }

    @Override
    public List<SaleTransaction> getSalesByCode(Long codeId) {
        return saleTransactionRepository.findByDiscountCode_Id(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesByInfluencer(Long influencerId) {
        return saleTransactionRepository.findByDiscountCode_Influencer_Id(influencerId);
    }

    @Override
    public List<SaleTransaction> getSalesByCampaign(Long campaignId) {
        return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
    }

    @Override
    public SaleTransaction getSaleById(Long id) {
        return saleTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }
}