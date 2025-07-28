# Photo-Storage-App

```
photo-app-root/
├── .gradle/                  # Gradle internal files (auto-generated)
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew                   # Gradle wrapper script (Linux/macOS)
├── gradlew.bat               # Gradle wrapper script (Windows)
├── settings.gradle.kts       # Gradle multi-module configuration
├── build.gradle.kts          # Root build file (for common configurations, e.g., dependency versions)
├── docker-compose.yml        # Docker Compose file to orchestrate all services
├── README.md                 # Project description and setup instructions
├── .gitignore                # Git ignore file

├── services/                 # Parent directory for all microservices
│   ├── photo-upload-service/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/com/example/photoupload/
│   │   │   │   │   ├── PhotoUploadApplication.java # Spring Boot main class
│   │   │   │   │   ├── controller/      # REST controllers (e.g., PhotoUploadController)
│   │   │   │   │   ├── service/         # Business logic (e.g., PhotoUploadService)
│   │   │   │   │   ├── config/          # Spring configurations
│   │   │   │   │   └── util/            # Utility classes
│   │   │   │   ├── resources/
│   │   │   │   │   ├── application.yml  # Spring Boot configuration
│   │   │   │   │   └── logback-spring.xml # Logging configuration
│   │   │   └── test/
│   │   │       └── java/com/example/photoupload/
│   │   │           └── ... (Unit and integration tests)
│   │   ├── build.gradle.kts   # Module-specific build file
│   │   └── Dockerfile         # Dockerfile for this service
│   │
│   └── photo-storage-service/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/com/example/photostorage/
│       │   │   │   ├── PhotoStorageApplication.java # Spring Boot main class
│   │   │   │   │   ├── controller/      # REST controllers (e.g., PhotoStorageController)
│   │   │   │   │   ├── service/         # Business logic (e.g., PhotoStorageService)
│   │   │   │   │   ├── repository/      # Spring Data JPA repositories (e.g., PhotoRepository)
│   │   │   │   │   ├── entity/          # JPA entities (e.g., Photo.java)
│   │   │   │   │   ├── config/          # Spring configurations
│   │   │   │   │   └── util/            # Utility classes
│   │   │   │   ├── resources/
│   │   │   │   │   ├── application.yml  # Spring Boot configuration
│   │   │   │   │   ├── logback-spring.xml # Logging configuration
│   │   │   │   │   └── db/migration/    # Flyway/Liquibase scripts (e.g., V1__create_photos_table.sql)
│   │   │   └── test/
│   │   │       └── java/com/example/photostorage/
│   │   │           └── ... (Unit and integration tests)
│       ├── build.gradle.kts   # Module-specific build file
│       └── Dockerfile         # Dockerfile for this service
│
├── desktop-client/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/photoclient/
│   │   │   │   ├── PhotoApp.java        # Main Swing application class
│   │   │   │   ├── gui/                 # JPanel classes, JFrame, custom components
│   │   │   │   ├── model/               # Data models for the GUI (e.g., PhotoModel)
│   │   │   │   ├── service/             # Client-side service for API calls to backend
│   │   │   │   └── util/                # Utility classes
│   │   │   ├── resources/
│   │   │   │   └── images/              # Application icons, default images
│   │   │   └── META-INF/
│   │   │       └── MANIFEST.MF          # For executable JAR
│   │   └── test/
│   │       └── java/com/example/photoclient/
│   │           └── ... (Unit tests for client-side logic)
│   ├── build.gradle.kts       # Module-specific build file
│   └── Dockerfile             # (Optional) Dockerfile if you want to containerize the desktop app
│
└── common/                   # (Optional) Shared module for common DTOs, enums, exceptions
    ├── src/
    │   ├── main/
    │   │   ├── java/com/example/common/
    │   │   │   ├── dto/                 # Data Transfer Objects (e.g., PhotoUploadRequest)
    │   │   │   └── exception/           # Custom exceptions
    │   │   └── resources/
    │   └── test/
    │       └── java/com/example/common/
    │           └── ...
    └── build.gradle.kts        # Module-specific build file
```
