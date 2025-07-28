package com.example.photoupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main application class for the Photo Upload Service.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
// @SpringBootApplication is a convenience annotation that adds:
// 1. @Configuration: Tags the class as a source of bean definitions.
// 2. @EnableAutoConfiguration: Enables Spring Boot's auto-configuration mechanism.
//    This tells Spring Boot to start adding beans based on classpath settings,
//    other beans, and various property settings.
// 3. @ComponentScan: Enables @Component scan on the package where the
//    application is located (com.example.photoupload) and its sub-packages.
//    This ensures that your controllers, services, repositories, and other
//    Spring components are discovered and registered.

@ConfigurationPropertiesScan
// This annotation tells Spring to scan for classes annotated with @ConfigurationProperties
// within the current package and its sub-packages. This is necessary for
// PhotoStorageServiceProperties to be properly loaded from application.yml.
public class PhotoUploadApplication {

    public static void main(String[] args) {
        // This is the main method that starts the Spring Boot application.
        // SpringApplication.run() bootstraps the application, creates the
        // ApplicationContext, and runs any embedded web servers (like Tomcat for web apps).
        SpringApplication.run(PhotoUploadApplication.class, args);
    }
}