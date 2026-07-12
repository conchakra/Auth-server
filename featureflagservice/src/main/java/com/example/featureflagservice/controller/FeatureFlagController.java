package com.example.featureflagservice.controller;

import com.example.featureflagservice.entity.FeatureFlag;
import com.example.featureflagservice.service.FeatureFlagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature-flags")
@CrossOrigin("*")
public class FeatureFlagController {

    private final FeatureFlagService service;

    public FeatureFlagController(
            FeatureFlagService service) {
        this.service = service;
    }

    @GetMapping
    public List<FeatureFlag> getAllFlags() {
        return service.getAllFlags();
    }

    @GetMapping("/{key}")
    public FeatureFlag getFlag(
            @PathVariable String key) {
        return service.getFlag(key);
    }

    @PutMapping("/{key}")
public FeatureFlag updateFlag(
        @PathVariable String key,
        @RequestBody FeatureFlag request) {

    FeatureFlag flag = service.getFlag(key);

    flag.setFlagValue(request.getFlagValue());

    return service.saveFlag(flag);
}

    @PostMapping
    public FeatureFlag createFlag(
            @RequestBody FeatureFlag flag) {
        return service.saveFlag(flag);
    }
}