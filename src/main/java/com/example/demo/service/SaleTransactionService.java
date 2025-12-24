package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import java.util.List;

public interface SaleTransactionService {
    SaleTransaction createSale(SaleTransaction transaction);
    List<SaleTransaction> getSalesByCode(Long codeId);
    List<SaleTransaction> getSalesByInfluencer(Long influencerId);
    List<SaleTransaction> getSalesByCampaign(Long campaignId); // Requirement from 1p.pdf Page 9
}