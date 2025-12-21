package com.example.demo.service.impl;

import com.example.demo.model.DiscountCode;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.DiscountCodeService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepository repository;

    public DiscountCodeServiceImpl(DiscountCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        if (repository.findByCode(code.getCode()).isPresent()) {
            throw new RuntimeException("Discount code must be unique");
        }
        return repository.save(code);
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return repository.findByInfluencer_Id(influencerId);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Code not found"));
        code.setActive(false);
        repository.save(code);
    }
}