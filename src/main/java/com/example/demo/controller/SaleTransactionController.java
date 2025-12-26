package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales Transactions")
public class SaleTransactionController {
    private final SaleTransactionService service;

    public SaleTransactionController(SaleTransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaleTransaction> logSale(@RequestBody SaleTransaction transaction) {
        return ResponseEntity.ok(service.createSale(transaction));
    }

   @GetMapping("/code/{codeId}")
public ResponseEntity<List<SaleTransaction>> getSalesByCode(@PathVariable Long codeId) {
    // Change method call below:
    return ResponseEntity.ok(service.getSalesForCode(codeId));
}

@GetMapping("/influencer/{influencerId}")
public ResponseEntity<List<SaleTransaction>> getSalesByInfluencer(@PathVariable Long influencerId) {
    // Change method call below:
    return ResponseEntity.ok(service.getSalesForInfluencer(influencerId));
}
}