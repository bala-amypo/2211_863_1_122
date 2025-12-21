package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DiscountCode;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.DiscountCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    // Constructor injection
    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        // Business Rule: Code string must be unique
        if (discountCodeRepository.findByCode(code.getCode()).isPresent()) {
            throw new RuntimeException("Discount code must be unique");
        }
        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode codeDetails) {
        DiscountCode code = getCodeById(id);
        code.setCode(codeDetails.getCode());
        code.setDiscountPercentage(codeDetails.getDiscountPercentage());
        code.setActive(codeDetails.getActive());
        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Code not found"));
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        // This resolves the missing method error from the previous build
        return discountCodeRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = getCodeById(id);
        code.setActive(false);
        discountCodeRepository.save(code);
    }
}