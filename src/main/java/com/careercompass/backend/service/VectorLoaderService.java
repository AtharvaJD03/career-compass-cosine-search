package com.careercompass.backend.service;

import com.careercompass.backend.model.CareerVector;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import com.careercompass.backend.model.RecommendationResponse;
//import java.util.Comparator;
import java.util.AbstractMap;
import java.util.stream.Collectors;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


@Service
public class VectorLoaderService {

    private List<CareerVector> careerVectors = new ArrayList<>();

    public List<CareerVector> getCareerVectors() {
        return careerVectors;
    }

    public List<String> findTopMatches(List<Double> inputVector, int topK) {

        if (inputVector == null || inputVector.isEmpty()) {
            throw new IllegalArgumentException("Input vector cannot be null or empty");
        }

        return careerVectors.stream()
                .map(career -> new AbstractMap.SimpleEntry<>(
                        career.getJob_role(),
                        cosineSimilarity(inputVector, career.getVector())
                ))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .filter(distinctByKey(entry -> entry.getKey()))
                .limit(topK)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private double cosineSimilarity(List<Double> v1, List<Double> v2) {

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < v1.size(); i++) {
            dotProduct += v1.get(i) * v2.get(i);
            normA += Math.pow(v1.get(i), 2);
            normB += Math.pow(v2.get(i), 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }



    @PostConstruct
    public void loadVectors() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("career_vectors.json");

            careerVectors = mapper.readValue(
                    inputStream,
                    new TypeReference<List<CareerVector>>() {}
            );

            System.out.println("Loaded vectors count: " + careerVectors.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}