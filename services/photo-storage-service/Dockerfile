# Use a lightweight OpenJDK 21 JRE image as the base image.
# 'slim-bullseye' is based on Debian Bullseye and is a good balance of size and functionality.
FROM eclipse-temurin:21-jdk-jammy


# Set the working directory inside the container.
# All subsequent commands will be executed relative to this directory.
WORKDIR /app

# Copy the Gradle build output (the executable JAR) into the container's /app directory.
# The `build/libs/photo-storage-service.jar` path assumes you've run `./gradlew bootJar`
# from the root of your multi-module project, which typically places the JAR there.
# Ensure the name 'photo-storage-service.jar' matches what you set in build.gradle.
COPY build/libs/photo-storage-service.jar photo-storage-service.jar

# Expose the port that the Spring Boot application runs on.
# This informs Docker that the container intends to listen on this port.
# (photo-storage-service uses port 8082 as defined in application.yml)
EXPOSE 8082

# Define the command to run the application when the container starts.
# 'java -jar' is the standard way to run an executable Spring Boot JAR.
# The '--spring.profiles.active=docker' part is optional but highly recommended
# for multi-environment deployments. It tells Spring Boot to load application-docker.yml.
ENTRYPOINT ["java", "-jar", "photo-storage-service.jar", "--spring.profiles.active=docker"]