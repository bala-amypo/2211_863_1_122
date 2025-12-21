package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import java.util.List;

public interface SaleTransactionService {
    SaleTransaction logSale(SaleTransaction transaction);
    SaleTransaction getSaleById(Long id);
    List<SaleTransaction> getSalesByCode(Long codeId);
    List<SaleTransaction> getSalesByInfluencer(Long influencerId);
    List<SaleTransaction> getSalesByCampaign(Long campaignId);
}