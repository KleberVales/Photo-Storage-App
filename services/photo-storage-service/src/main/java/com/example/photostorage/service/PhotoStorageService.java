package com.example.photostorage.service;

import com.example.common.dto.PhotoUploadRequest; // Import the shared DTO
import com.example.photostorage.entity.Photo;
import com.example.photostorage.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import for transactional control

/**
 * Service class responsible for the business logic of storing photos.
 * It transforms incoming DTOs into entities and interacts with the repository.
 */
@Service // Marks this class as a Spring Service component
public class PhotoStorageService {

    private final PhotoRepository photoRepository;

    // Spring will automatically inject an instance of PhotoRepository
    public PhotoStorageService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /**
     * Stores a photo in the database based on the provided PhotoUploadRequest.
     * This method is transactional, ensuring data consistency.
     *
     * @param request The PhotoUploadRequest containing photo metadata and binary data.
     * @return The filename of the stored photo.
     * @throws RuntimeException if the photo cannot be stored.
     */
    @Transactional // Ensures this method runs within a database transaction
    public String storePhoto(PhotoUploadRequest request) {
        System.out.println("Storing photo: " + request.getFilename());

        // 1. Convert DTO (PhotoUploadRequest) to Entity (Photo)
        // Using Lombok's @Builder to create the Photo entity from the request data.
        Photo photo = Photo.builder()
                .filename(request.getFilename())
                .contentType(request.getContentType())
                .description(request.getDescription())
                .imageData(request.getImageData())
                // uploadTimestamp will be automatically set by @CreationTimestamp in the Photo entity
                .build();

        // 2. Save the Photo entity using the repository
        try {
            Photo savedPhoto = photoRepository.save(photo);
            System.out.println("Photo saved to DB with ID: " + savedPhoto.getId());
            return savedPhoto.getFilename(); // Return the filename of the successfully stored photo
        } catch (Exception e) {
            // Log the error and re-throw a more specific exception if needed,
            // or a generic runtime exception.
            System.err.println("Error saving photo to database: " + e.getMessage());
            throw new RuntimeException("Failed to store photo in database: " + request.getFilename(), e);
        }
    }

    // You could add other methods here for retrieving photos, deleting photos, etc.
    // e.g., public Photo getPhotoById(Long id) { return photoRepository.findById(id).orElse(null); }
}