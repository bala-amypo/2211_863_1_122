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

    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        // Validation for uniqueness can be added here
        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencer_Id(influencerId);
    }

    // Resolves "does not override abstract method getCodesByCampaign"
    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode codeDetails) {
        DiscountCode code = getCodeById(id);
        
        // Fixes "cannot find symbol" by using correct model getters
        code.setCode(codeDetails.getCode());
        code.setDiscountPercentage(codeDetails.getDiscountPercentage());
        code.setActive(codeDetails.getActive());
        
        return discountCodeRepository.save(code);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = getCodeById(id);
        code.setActive(false); // Fixes setActive(boolean) symbol error
        discountCodeRepository.save(code);
    }
}