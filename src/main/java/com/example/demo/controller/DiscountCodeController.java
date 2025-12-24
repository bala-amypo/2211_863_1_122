package com.example.demo.controller;

import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discount-codes")
@Tag(name = "Discount Codes")
public class DiscountCodeController {
    private final DiscountCodeService service;

    public DiscountCodeController(DiscountCodeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiscountCode> create(@RequestBody DiscountCode code) {
        return ResponseEntity.ok(service.createDiscountCode(code));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<DiscountCode>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(service.getCodesByInfluencer(influencerId));
    }
}