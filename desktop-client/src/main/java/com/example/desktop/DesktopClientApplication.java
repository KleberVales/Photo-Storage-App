package com.example.desktop;

import javafx.application.Application; // The base class for all JavaFX applications
import javafx.fxml.FXMLLoader;       // To load FXML files
import javafx.scene.Parent;          // The base class for all nodes that have children
import javafx.scene.Scene;           // The container for all content in a scene graph
import javafx.stage.Stage;           // The top-level container for JavaFX applications

import java.io.IOException;          // For handling potential FXML loading errors

/**
 * Main application class for the Photo Upload Desktop Client.
 * This class extends JavaFX's Application and sets up the primary stage (window)
 * and loads the main FXML UI.
 */
public class DesktopClientApplication extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage The primary stage for this application, onto which
     * the application scene can be set. The first stage
     * is constructed by the platform.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create an FXMLLoader instance to load your FXML file.
        // It looks for the FXML file in the 'resources' folder of your module.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DesktopClientView.fxml"));

        // Load the FXML file and get the root node of the scene graph.
        // Parent is the base class for all nodes that can have children in a scene graph.
        Parent root = loader.load();

        // Set the title of the application window.
        primaryStage.setTitle("Photo Uploader Client");

        // Create a new scene with the loaded root node and define its initial dimensions.
        Scene scene = new Scene(root, 700, 500); // Width, Height

        // Set the scene onto the primary stage.
        primaryStage.setScene(scene);

        // Display the stage (window) to the user.
        primaryStage.show();
    }

    /**
     * The main method is ignored in a JavaFX application when it is launched from the command line,
     * or from a JAR file by double-clicking.
     *
     * However, it is used when you run the application from an IDE.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // This method calls the launch() method, which is inherited from Application.
        // The launch() method starts the JavaFX runtime and then calls the start() method.
        launch(args);
    }
}