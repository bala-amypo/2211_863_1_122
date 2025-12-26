package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleTransactionController {

    private final SaleTransactionService service;

    public SaleTransactionController(SaleTransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaleTransaction> createSale(@RequestBody SaleTransaction transaction) {
        return ResponseEntity.ok(service.createSale(transaction));
    }

    @GetMapping("/code/{codeId}")
    public ResponseEntity<List<SaleTransaction>> getSalesForCode(@PathVariable Long codeId) {
        return ResponseEntity.ok(service.getSalesForCode(codeId));
    }
}