package com.careercompass.backend.controller;

import com.careercompass.backend.service.VectorLoaderService;
import org.springframework.web.bind.annotation.*;
import com.careercompass.backend.model.RecommendationResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final VectorLoaderService vectorService;

    public RecommendationController(VectorLoaderService vectorService) {
        this.vectorService = vectorService;
    }

    @PostMapping("/recommend")
    public List<RecommendationResponse> recommend(@RequestBody List<Double> inputVector) {
        return vectorService.findTopMatches(inputVector, 3);
    }

    @GetMapping("/recommend-test")
    public List<RecommendationResponse> recommendTest() {

        List<Double> sampleInput = List.of(
                0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7,
                0.8, 0.9, 0.1, 0.2, 0.3, 0.4,
                0.5, 0.6, 0.7, 0.8
        );

        return vectorService.findTopMatches(sampleInput, 3);
    }
}