package com.example.demo.controller;

import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/codes")
public class DiscountCodeController {
    private final DiscountCodeService codeService;

    public DiscountCodeController(DiscountCodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping
    public DiscountCode create(@RequestBody DiscountCode code) {
        return codeService.createDiscountCode(code);
    }

    @GetMapping("/influencer/{influencerId}")
    public List<DiscountCode> getByInfluencer(@PathVariable Long influencerId) {
        return codeService.getCodesByInfluencer(influencerId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        codeService.deactivateCode(id);
    }
}