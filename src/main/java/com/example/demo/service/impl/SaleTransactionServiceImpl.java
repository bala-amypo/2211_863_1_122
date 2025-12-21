package com.example.demo.service.impl;

import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.SaleTransactionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {

    private final SaleTransactionRepository saleRepo;
    private final DiscountCodeRepository codeRepo;

    public SaleTransactionServiceImpl(SaleTransactionRepository saleRepo, 
                                      DiscountCodeRepository codeRepo) {
        this.saleRepo = saleRepo;
        this.codeRepo = codeRepo;
    }
@Override
public List<SaleTransaction> getSalesByCampaign(Long campaignId) {
    // This override fixes the error in SaleTransactionServiceImpl
    return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
}
    @Override
    public SaleTransaction logSale(SaleTransaction transaction) {
        // Validation: Sale must be a positive number
        if (transaction.getSaleAmount() == null || transaction.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transaction amount must be positive");
        }

        // Attribution check: Ensure the discount code exists
        if (transaction.getDiscountCode() == null || transaction.getDiscountCode().getId() == null) {
            throw new RuntimeException("Sale must be associated with a valid discount code");
        }
        
        codeRepo.findById(transaction.getDiscountCode().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));

        return saleRepo.save(transaction);
    }

    @Override
    public SaleTransaction getSaleById(Long id) {
        return saleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
    }

    @Override
    public List<SaleTransaction> getSalesByCode(Long codeId) {
        return saleRepo.findByDiscountCode_Id(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesByInfluencer(Long influencerId) {
        return saleRepo.findByDiscountCode_Influencer_Id(influencerId);
    }

    // This method implementation resolves the abstract method error
    @Override
    public List<SaleTransaction> getSalesByCampaign(Long campaignId) {
        return saleRepo.findByDiscountCode_Campaign_Id(campaignId);
    }
}