package com.example.featureflagservice.impl;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.repository.FeatureFlagRepository;
import com.example.featureflagservice.service.FeatureFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagServiceImpl
        implements FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagServiceImpl(
            FeatureFlagRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FeatureFlag> getAllFlags() {
        return repository.findAll();
    }

    @Override
    public FeatureFlag getFlag(String key) {
        return repository.findByFlagKey(key)
                .orElseThrow(() ->
                        new RuntimeException("Flag not found"));
    }

    @Override
    public FeatureFlag saveFlag(FeatureFlag flag) {
        return repository.save(flag);
    }
}