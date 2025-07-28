package com.example.photoupload.util;

/**
 * Utility class for common image-related operations and validations
 * within the photo-upload-service.
 * This class contains static methods and is not intended for instantiation.
 */
public class ImageUtil {

    // Private constructor to prevent instantiation
    private ImageUtil() {
        // Utility classes typically don't need to be instantiated
    }

    /**
     * Validates if the given image data array is within an acceptable size limit.
     * This is a simple example; real-world validation might check dimensions, format, etc.
     *
     * @param imageData The byte array of the image.
     * @param maxSizeInBytes The maximum allowed size for the image in bytes.
     * @return true if the image data size is within the limit and not null/empty, false otherwise.
     */
    public static boolean isValidImageSize(byte[] imageData, long maxSizeInBytes) {
        if (imageData == null || imageData.length == 0) {
            System.err.println("ImageUtil: Image data is null or empty.");
            return false;
        }
        if (imageData.length > maxSizeInBytes) {
            System.err.println("ImageUtil: Image size (" + imageData.length + " bytes) exceeds maximum allowed size (" + maxSizeInBytes + " bytes).");
            return false;
        }
        return true;
    }

    // You could add more utility methods here, such as:
    // public static boolean isValidImageContentType(String contentType) { ... }
    // public static byte[] resizeImage(byte[] originalImage, int maxWidth, int maxHeight) { ... }
    // public static String generateUniqueFilename(String originalFilename) { ... }
}