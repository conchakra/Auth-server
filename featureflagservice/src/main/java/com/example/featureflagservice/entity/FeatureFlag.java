package com.example.featureflagservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feature_flags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String flagKey;

    @Column(nullable = false)
    private Boolean flagValue;

    private String description;
}