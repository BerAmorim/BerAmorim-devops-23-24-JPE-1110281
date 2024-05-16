# First stage: Build the application
FROM gradle:jdk17 as builder
LABEL author="Bernardo Amorim"

WORKDIR /ca4-part1

# Copy just the necessary files to build the application
COPY ./gradle_basic_demo /ca4-part1

# Execute the build
RUN ./gradlew clean build

# Second stage: Create the final image
FROM openjdk:17-jdk-slim

# Copy the jar file from the builder stage
COPY --from=builder /ca4-part1/build/libs/*.jar ca4-part1.jar

# Expose the port used by the chat server
EXPOSE 59001

# Specify the command to run the application
ENTRYPOINT ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]

