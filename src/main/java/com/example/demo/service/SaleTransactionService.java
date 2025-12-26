package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import java.util.List;

public interface SaleTransactionService {
    SaleTransaction createSale(SaleTransaction transaction);
    List<SaleTransaction> getSalesForCode(Long codeId);
    List<SaleTransaction> getSalesForInfluencer(Long influencerId);
    List<SaleTransaction> getSalesForCampaign(Long campaignId);
    SaleTransaction getSaleById(Long id);
}