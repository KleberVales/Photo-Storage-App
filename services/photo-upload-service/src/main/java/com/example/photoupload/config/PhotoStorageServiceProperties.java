package com.example.photoupload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for connecting to the Photo Storage Service.
 * Binds properties prefixed with "photo-storage" from application.yml.
 */
@Component // Makes this class a Spring managed component (a bean)
@ConfigurationProperties(prefix = "photo-storage") // Binds properties starting with "photo-storage."
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
public class PhotoStorageServiceProperties {

    /**
     * The base URL of the Photo Storage Service API.
     * Example: http://localhost:8082 or http://photo-storage-service:8082 (in Docker)
     */
    private String serviceUrl;

    // You could add other properties here if needed, e.g.,
    // private int connectionTimeout;
    // private int readTimeout;
}