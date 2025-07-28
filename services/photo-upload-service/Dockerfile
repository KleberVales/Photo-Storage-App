# Use a lightweight OpenJDK 21 JRE image as the base image.
# 'slim-bullseye' is based on Debian Bullseye and is a good balance of size and functionality.
FROM openjdk:21-jre-slim-bullseye

# Set the working directory inside the container.
# All subsequent commands will be executed relative to this directory.
WORKDIR /app

# Copy the Gradle build output (the executable JAR) into the container's /app directory.
# The `build/libs/photo-upload-service.jar` path assumes you've run `./gradlew bootJar`
# from the root of your multi-module project, which typically places the JAR there.
# Ensure the name 'photo-upload-service.jar' matches what you set in build.gradle.
COPY build/libs/photo-upload-service.jar photo-upload-service.jar

# Expose the port that the Spring Boot application runs on.
# This informs Docker that the container intends to listen on this port.
# (photo-upload-service uses port 8081 as defined in application.yml)
EXPOSE 8081

# Define the command to run the application when the container starts.
# 'java -jar' is the standard way to run an executable Spring Boot JAR.
# The '--spring.profiles.active=docker' part is optional but highly recommended
# for multi-environment deployments. We'll set up a 'docker' profile in application-docker.yml
# later to switch configurations (e.g., service URLs).
ENTRYPOINT ["java", "-jar", "photo-upload-service.jar"]
# CMD ["--spring.profiles.active=docker"] # You could pass profile as CMD if you prefer