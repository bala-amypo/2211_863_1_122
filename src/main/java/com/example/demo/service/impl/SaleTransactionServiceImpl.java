package com.example.demo.service.impl;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import com.example.demo.repository.SaleTransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {
    private final SaleTransactionRepository saleTransactionRepository;

    public SaleTransactionServiceImpl(SaleTransactionRepository saleTransactionRepository) {
        this.saleTransactionRepository = saleTransactionRepository;
    }

    // Resolves "does not override abstract method getSalesByCampaign"
    @Override
    public List<SaleTransaction> getSalesByCampaign(Long campaignId) {
        return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
    }
    
    // implement other methods like logSale, getSaleById, etc.
}