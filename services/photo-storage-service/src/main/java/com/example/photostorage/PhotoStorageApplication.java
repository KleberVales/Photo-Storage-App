package com.example.photostorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // Import for JPA repositories

/**
 * Main application class for the Photo Storage Service.
 * This class bootstraps the Spring Boot application and enables JPA repository scanning.
 */
@SpringBootApplication
// @SpringBootApplication automatically enables @ComponentScan for this package
// (com.example.photostorage) and its sub-packages.

@EnableJpaRepositories(basePackages = "com.example.photostorage.repository")
// This annotation explicitly enables Spring Data JPA repositories.
// It tells Spring where to find repository interfaces that extend JpaRepository.
public class PhotoStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoStorageApplication.class, args);
    }
}