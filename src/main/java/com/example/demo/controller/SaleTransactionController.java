package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}