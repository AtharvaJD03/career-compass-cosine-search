package com.careercompass.backend.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecommendationResponse {

    private String jobRole;
    private double similarityScore;

    public RecommendationResponse(String jobRole, double similarityScore) {
        this.jobRole = jobRole;

        // ✅ Round to 4 decimal places
        this.similarityScore = BigDecimal
                .valueOf(similarityScore)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getJobRole() {
        return jobRole;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }
}