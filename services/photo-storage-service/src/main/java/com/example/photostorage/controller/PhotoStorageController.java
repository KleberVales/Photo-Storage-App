package com.example.photostorage.controller;

import com.example.common.dto.PhotoUploadRequest; // Import the shared DTO
import com.example.photostorage.service.PhotoStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for handling photo storage requests.
 * This API is primarily consumed by the photo-upload-service.
 */
@RestController // Marks this class as a REST Controller
@RequestMapping("/api/v1/photos") // Base path for photo storage operations
public class PhotoStorageController {

    private final PhotoStorageService photoStorageService;

    // Spring will automatically inject an instance of PhotoStorageService
    public PhotoStorageController(PhotoStorageService photoStorageService) {
        this.photoStorageService = photoStorageService;
    }

    /**
     * Handles POST requests to store a new photo in the database.
     * The photo data and metadata are expected in the request body as a PhotoUploadRequest JSON object.
     *
     * @param request The PhotoUploadRequest object containing photo filename, content type, image data, and description.
     * @return A ResponseEntity<String> indicating the success or failure of the storage operation.
     */
    @PostMapping // Maps HTTP POST requests to this method
    public ResponseEntity<String> storePhoto(@RequestBody PhotoUploadRequest request) {
        // --- Basic Input Validation (can be expanded with @Valid and JSR-380 annotations) ---
        if (request.getImageData() == null || request.getImageData().length == 0) {
            System.err.println("Validation Error: Image data is empty for storage request.");
            return ResponseEntity.badRequest().body("Image data cannot be empty.");
        }
        if (request.getFilename() == null || request.getFilename().trim().isEmpty()) {
            System.err.println("Validation Error: Filename is empty for storage request.");
            return ResponseEntity.badRequest().body("Filename cannot be empty.");
        }
        if (request.getContentType() == null || request.getContentType().trim().isEmpty()) {
            System.err.println("Validation Error: Content type is empty for storage request.");
            return ResponseEntity.badRequest().body("Content type cannot be empty.");
        }

        System.out.println("Received photo storage request for file: " + request.getFilename() +
                ", Size: " + (request.getImageData().length / 1024) + " KB");

        try {
            // Delegate the actual photo saving logic to the PhotoStorageService
            String storedFilename = photoStorageService.storePhoto(request);
            System.out.println("Photo successfully stored: " + storedFilename);
            return ResponseEntity.status(HttpStatus.CREATED).body("Photo stored successfully: " + storedFilename);
        } catch (Exception e) {
            System.err.println("Failed to store photo " + request.getFilename() + ". Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store photo: " + e.getMessage());
        }
    }
}