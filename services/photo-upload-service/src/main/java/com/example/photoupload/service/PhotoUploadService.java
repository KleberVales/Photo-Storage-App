package com.example.photoupload.service;

import com.example.common.dto.PhotoUploadRequest; // Import the shared DTO
import com.example.photoupload.config.PhotoStorageServiceProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service class responsible for handling the business logic of uploading photos.
 * This includes forwarding photo data to the Photo Storage Service.
 */
@Service // Marks this class as a Spring Service component
public class PhotoUploadService {

    private final WebClient webClient; // WebClient for making HTTP requests

    /**
     * Constructor for PhotoUploadService, injecting WebClient.Builder and PhotoStorageServiceProperties.
     * WebClient is configured to target the Photo Storage Service.
     *
     * @param webClientBuilder Spring-provided builder for WebClient.
     * @param properties Configuration properties for Photo Storage Service URL.
     */
    public PhotoUploadService(WebClient.Builder webClientBuilder, PhotoStorageServiceProperties properties) {
        // Build a WebClient instance with the base URL of the Photo Storage Service.
        // The base URL is retrieved from the PhotoStorageServiceProperties,
        // which reads it from application.yml (e.g., http://localhost:8082 or http://photo-storage-service:8082).
        this.webClient = webClientBuilder.baseUrl(properties.getServiceUrl()).build();
        System.out.println("PhotoUploadService initialized. Photo Storage Service URL: " + properties.getServiceUrl());
    }

    /**
     * Uploads a photo by sending the PhotoUploadRequest to the Photo Storage Service.
     * This operation is non-blocking and reactive, returning a Mono.
     *
     * @param request The PhotoUploadRequest containing the photo's filename, content type, image data, and description.
     * @return A Mono<String> that emits the response body from the Photo Storage Service upon success,
     * or an error signal if the request fails.
     */
    public Mono<String> uploadPhoto(PhotoUploadRequest request) {
        System.out.println("Attempting to upload photo: " + request.getFilename() + " to Photo Storage Service.");

        return webClient.post() // Initiate a POST request
                .uri("/api/v1/photos") // Specify the relative URI path on the Photo Storage Service
                // This combines with the base URL (e.g., http://localhost:8082/api/v1/photos)
                .contentType(MediaType.APPLICATION_JSON) // Set the Content-Type header to application/json
                .bodyValue(request) // Set the request body. Spring will serialize PhotoUploadRequest to JSON.
                .retrieve() // Execute the request and retrieve the response
                .bodyToMono(String.class) // Extract the response body as a Mono of String (e.g., the success message from photo-storage-service)
                .doOnSuccess(response -> System.out.println("Successfully sent photo to Photo Storage Service. Response: " + response))
                .doOnError(error -> System.err.println("Failed to send photo to Photo Storage Service for " + request.getFilename() + ". Error: " + error.getMessage()));
    }
}