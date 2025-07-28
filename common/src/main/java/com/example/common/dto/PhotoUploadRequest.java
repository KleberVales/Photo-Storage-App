package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for photo upload requests.
 * Used to transfer photo data and metadata between services.
 */
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
public class PhotoUploadRequest {

    private String filename;
    private String contentType; // e.g., "image/jpeg", "image/png"
    private byte[] imageData;
    private String description;

    // You could add validation annotations here if you want to use JSR-380 validation
    // common across services, e.g.:
    // @NotBlank(message = "Filename cannot be empty")
    // @Size(max = 255, message = "Filename cannot exceed 255 characters")
    // etc.
}