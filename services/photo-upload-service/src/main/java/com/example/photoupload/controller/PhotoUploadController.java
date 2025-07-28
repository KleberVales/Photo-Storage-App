package com.example.photoupload.controller;

import com.example.common.dto.PhotoUploadRequest; // Import the shared DTO
import com.example.photoupload.service.PhotoUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST Controller for handling photo upload requests from clients.
 * Exposes an API endpoint for clients to send photo data.
 */
@RestController // Marks this class as a REST Controller, handling incoming web requests
@RequestMapping("/api/v1/upload") // Base path for all endpoints in this controller
public class PhotoUploadController {

    private final PhotoUploadService photoUploadService;

    // Spring will automatically inject an instance of PhotoUploadService
    // because it's marked with @Service and this is a component
    public PhotoUploadController(PhotoUploadService photoUploadService) {
        this.photoUploadService = photoUploadService;
    }

    /**
     * Handles POST requests to upload a photo.
     * The photo data and metadata are expected in the request body as a PhotoUploadRequest JSON object.
     *
     * @param request The PhotoUploadRequest object containing photo filename, content type, image data, and description.
     * @return A Mono<ResponseEntity<String>> indicating the success or failure of the upload.
     */
    @PostMapping // Maps HTTP POST requests to this method
    public Mono<ResponseEntity<String>> uploadPhoto(@RequestBody PhotoUploadRequest request) {
        // --- Basic Input Validation ---
        // More sophisticated validation (e.g., using JSR-380 annotations like @NotNull, @Size)
        // could be added to the PhotoUploadRequest DTO and enabled here with @Valid.
        if (request.getImageData() == null || request.getImageData().length == 0) {
            System.out.println("Validation Error: Image data is empty for upload request.");
            return Mono.just(ResponseEntity.badRequest().body("Image data cannot be empty."));
        }
        if (request.getFilename() == null || request.getFilename().trim().isEmpty()) {
            System.out.println("Validation Error: Filename is empty for upload request.");
            return Mono.just(ResponseEntity.badRequest().body("Filename cannot be empty."));
        }
        // You might also want to validate contentType here, e.g., only "image/jpeg", "image/png"

        System.out.println("Received upload request for file: " + request.getFilename() +
                ", Size: " + (request.getImageData().length / 1024) + " KB");

        // Delegate the actual photo saving logic to the PhotoUploadService
        return photoUploadService.uploadPhoto(request)
                .map(response -> {
                    // If the upload to photo-storage-service was successful
                    System.out.println("Upload successful for file: " + request.getFilename() + ". Storage service response: " + response);
                    return ResponseEntity.ok("Upload successful: " + response);
                })
                .onErrorResume(e -> {
                    // If an error occurred during the upload process (e.g., network issue to storage service)
                    System.err.println("Upload failed for file: " + request.getFilename() + ". Error: " + e.getMessage());
                    // Log the full stack trace for debugging
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage()));
                });
    }
}