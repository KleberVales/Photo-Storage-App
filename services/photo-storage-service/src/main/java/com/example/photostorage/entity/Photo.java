package com.example.photostorage.entity;

import jakarta.persistence.*; // Import Jakarta Persistence API annotations
import lombok.AllArgsConstructor;
import lombok.Builder; // Useful for creating Photo objects in tests/builders
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a stored photo entity in the database.
 * Maps to the 'photos' table.
 */
@Entity // Marks this class as a JPA entity, mapping to a database table
@Table(name = "photos") // Specifies the name of the database table (optional, defaults to class name)
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all fields
@Builder // Lombok: Provides a builder pattern for creating instances
public class Photo {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID using database identity column
    private Long id;

    @Column(nullable = false, length = 255) // Maps to a column, not nullable, max length 255
    private String filename;

    @Column(name = "content_type", nullable = false, length = 100) // Custom column name, not nullable, max length 100
    private String contentType;

    @Lob // Specifies that this field holds a large object (BLOB for binary data)
    @Column(nullable = false, columnDefinition = "BYTEA") // Maps to a column, not nullable. BYTEA is PostgreSQL's type for binary data.
    private byte[] imageData;

    @Column(length = 1000) // Optional description, max length 1000
    private String description;

    @CreationTimestamp // Hibernate annotation: automatically sets the timestamp when the entity is first persisted
    @Column(name = "upload_timestamp", nullable = false, updatable = false) // Column name, not nullable, not updatable after creation
    private LocalDateTime uploadTimestamp;
}