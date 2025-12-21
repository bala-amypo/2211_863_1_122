package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleTransactionController {

    private final SaleTransactionService saleTransactionService;

    // Constructor injection as per project rules
    public SaleTransactionController(SaleTransactionService saleTransactionService) {
        this.saleTransactionService = saleTransactionService;
    }

    @PostMapping
    public ResponseEntity<SaleTransaction> logSale(@RequestBody SaleTransaction transaction) {
        SaleTransaction createdSale = saleTransactionService.logSale(transaction);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleTransaction> getSaleById(@PathVariable Long id) {
        SaleTransaction sale = saleTransactionService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/code/{codeId}")
    public ResponseEntity<List<SaleTransaction>> getSalesByCode(@PathVariable Long codeId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesByCode(codeId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<SaleTransaction>> getSalesByInfluencer(@PathVariable Long influencerId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesByInfluencer(influencerId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<SaleTransaction>> getSalesByCampaign(@PathVariable Long campaignId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesByCampaign(campaignId);
        return ResponseEntity.ok(sales);
    }
}