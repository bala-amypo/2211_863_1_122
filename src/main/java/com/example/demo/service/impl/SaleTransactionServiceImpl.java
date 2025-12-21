package com.example.demo.service.impl;

import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.SaleTransactionService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {
    private final SaleTransactionRepository saleRepo;
    private final DiscountCodeRepository codeRepo;

    // Specific constructor order [cite: 1147]
    public SaleTransactionServiceImpl(SaleTransactionRepository saleRepo, DiscountCodeRepository codeRepo) {
        this.saleRepo = saleRepo;
        this.codeRepo = codeRepo;
    }

    @Override
    public SaleTransaction createSale(SaleTransaction tx) {
        if (tx.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be > 0"); // [cite: 757, 1185]
        }
        if (tx.getTransactionDate() == null) {
            tx.setTransactionDate(new Timestamp(System.currentTimeMillis())); // [cite: 758]
        }
        return saleRepo.save(tx);
    }
}