package com.example.featureflagservice.repository;

import com.example.featureflagservice.entity.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureFlagRepository
        extends JpaRepository<FeatureFlag,String> {

    Optional<FeatureFlag> findByFlagKey(String flagKey);
}