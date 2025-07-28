package com.example.desktop;

import com.example.common.dto.PhotoUploadRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.ResourceBundle; // <--- Import ResourceBundle

public class DesktopClientController {

    @FXML private TextField filePathField;
    @FXML private TextField descriptionField;
    @FXML private Button uploadButton;
    @FXML private Label statusLabel;

    private File selectedFile;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Use a field to store the loaded URL
    private String uploadServiceUrl; // <--- Declare field to hold the URL

    /**
     * Initializes the controller. This method is called automatically after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        // Load properties here
        try {
            // "application" refers to application.properties (without the .properties extension)
            ResourceBundle bundle = ResourceBundle.getBundle("application");
            this.uploadServiceUrl = bundle.getString("photo.upload.service.url");
            System.out.println("Loaded upload service URL: " + this.uploadServiceUrl);
        } catch (Exception e) {
            System.err.println("Error loading application properties: " + e.getMessage());
            statusLabel.setText("Error: Could not load service URL from properties.");
            uploadButton.setDisable(true); // Disable upload if config fails
            return; // Prevent further execution if critical config is missing
        }

        updateUploadButtonState();
        filePathField.textProperty().addListener((obs, oldText, newText) -> updateUploadButtonState());
    }

    // ... (rest of the handleBrowseButton() method remains the same) ...
    @FXML
    private void handleBrowseButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage stage = (Stage) filePathField.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        } else {
            filePathField.setText("");
        }
        updateUploadButtonState();
    }


    /**
     * Handles the action when the "Upload Photo" button is clicked.
     * Reads the selected file, constructs a DTO, and sends it to the backend.
     */
    @FXML
    private void handleUploadButton() {
        if (selectedFile == null) {
            statusLabel.setText("Please select a file first.");
            return;
        }

        try {
            byte[] imageData = Files.readAllBytes(selectedFile.toPath());
            String filename = selectedFile.getName();
            String contentType = Files.probeContentType(selectedFile.toPath());

            if (contentType == null) {
                contentType = "application/octet-stream";
                System.out.println("Warning: Could not determine content type for " + filename + ". Using default.");
            }

            PhotoUploadRequest requestDto = new PhotoUploadRequest(
                    filename,
                    contentType,
                    imageData,
                    descriptionField.getText()
            );

            String requestBodyJson = objectMapper.writeValueAsString(requestDto);

            // Use the loaded URL here!
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.uploadServiceUrl)) // <--- Use the loaded URL
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                    .build();

            statusLabel.setText("Uploading " + filename + "...");
            System.out.println("Sending upload request to: " + this.uploadServiceUrl); // <--- Use the loaded URL

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(responseBody -> {
                        Platform.runLater(() -> {
                            System.out.println("Upload response: " + responseBody);
                            statusLabel.setText("Upload successful: " + responseBody);
                            filePathField.setText("");
                            descriptionField.setText("");
                            selectedFile = null;
                            updateUploadButtonState();
                        });
                    })
                    .exceptionally(e -> {
                        Platform.runLater(() -> {
                            String errorMessage = "Upload failed: " + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                            System.err.println(errorMessage);
                            e.printStackTrace();
                            statusLabel.setText(errorMessage);
                        });
                        return null;
                    });

        } catch (IOException e) {
            statusLabel.setText("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            statusLabel.setText("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateUploadButtonState() {
        // Ensure upload button is disabled if URL failed to load
        uploadButton.setDisable(selectedFile == null || filePathField.getText().isEmpty() || uploadServiceUrl == null);
    }
}