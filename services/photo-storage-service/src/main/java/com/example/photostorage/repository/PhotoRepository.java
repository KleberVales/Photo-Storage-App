package com.example.photostorage.repository;

import com.example.photostorage.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for the Photo entity.
 * Provides standard CRUD operations and allows for custom query methods.
 */
@Repository // Marks this interface as a Spring Data JPA repository component
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // JpaRepository<Entity, ID_Type>
    // - 'Photo' is the entity class this repository manages.
    // - 'Long' is the data type of the primary key (id) of the Photo entity.

    // Spring Data JPA automatically provides methods like:
    // - save(Photo photo): Saves a Photo entity (insert or update)
    // - findById(Long id): Retrieves a Photo by its ID
    // - findAll(): Retrieves all Photo entities
    // - deleteById(Long id): Deletes a Photo by its ID
    // - etc.

    // You can define custom query methods here by following Spring Data JPA's naming conventions,
    // e.g., Photo findByFilename(String filename);
    // Or by using @Query annotation for more complex queries.
}