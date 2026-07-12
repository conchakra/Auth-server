package com.example.featureflagservice.service;

import com.example.featureflagservice.entity.FeatureFlag;

import java.util.List;

public interface FeatureFlagService {

    List<FeatureFlag> getAllFlags();

    FeatureFlag getFlag(String key);

    FeatureFlag saveFlag(FeatureFlag flag);
}