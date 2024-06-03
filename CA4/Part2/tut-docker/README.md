
# Docker Project for Spring Boot and H2 Database

## Project Description

This project demonstrates how to set up a containerized environment using Docker to run a version of the Spring Boot basic tutorial application using Gradle. The project uses two Docker containers: one for Tomcat running the Spring Boot application and another for the H2 database.


## Docker Compose

The `docker-compose.yml` file defines the `db` and `web` services for the H2 database and the Spring Boot application, respectively.

```yaml
services:
   db:
      build:
         context: .
         dockerfile: Dockerfile-db
      container_name: h2-db
      ports:
         - "8082:8082"
         - "9092:9092"
      volumes:
         - h2-data:/opt/h2-data
         - db-backup:/backup

   web:
      build:
         context: .
         dockerfile: Dockerfile-web
      container_name: spring-web
      ports:
         - "8080:8080"
      depends_on:
         - db

volumes:
   h2-data:
      driver: local
   db-backup:
      driver: local
```

## Dockerfile for the Database (Dockerfile-db)

The `Dockerfile-db` sets up an Ubuntu container with the H2 Database.

```dockerfile
FROM ubuntu:focal
RUN apt-get update && \
    apt-get install -y wget openjdk-17-jdk-headless && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /opt/h2
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar
EXPOSE 8082
EXPOSE 9092
CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092", "-baseDir", "/opt/h2-data"]
```

## Dockerfile for the Web Application (Dockerfile-web)

The `Dockerfile-web` sets up a container for running the Spring Boot application.

```dockerfile
# Use OpenJDK 17 image from Docker Hub
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Expose port 8080 for the web server
EXPOSE 8080

# Copy the built WAR file from your build directory to the container
COPY ./build/libs/basic-0.0.1-SNAPSHOT.war /app/

# Command to run the WAR file
CMD ["java", "-jar", "basic-0.0.1-SNAPSHOT.war"]
```

## Steps to Run the Project

1. **Build the Project:**
   Place your Gradle project in the `tut-docker` directory inside `CA4/Part2` and build it using Gradle.

   ```bash
   ./gradlew build
   ```

2. **Build and Run Docker Containers:**
   Use Docker Compose to build and run the containers.

   ```bash
   docker-compose up --build
   ```

3. **Access the Application:**
    - The H2 Database console can be accessed at `http://localhost:8082`
    - JDBC URL: `jdbc:h2:tcp://db:9092/./jpadb`
    - The Spring Boot application can be accessed at `http://localhost:8080/basic-0.0.1-SNAPSHOT/`

## Publishing to Docker Hub

1. **Tag your Images:**
   Tag the built images before pushing them to Docker Hub.

   ```bash
   docker tag h2-db your_dockerhub_username/tut-docker-db:latest
   docker tag spring-web your_dockerhub_username/tut-docker-web:latest
   ```

2. **Push the Images:**
   Push the tagged images to Docker Hub.

   ```bash
   docker push your_dockerhub_username/tut-docker-db:latest
   docker push your_dockerhub_username/tut-docker-web:latest
   ```
   
## Copy the database file to the volume

```bash
docker exec h2-db cp /opt/h2-data/database-file /backup/database-file
```

## Conclusion

This README provides the necessary steps to set up and run a containerized Spring Boot application with an H2 database using Docker. Ensure all Docker-related files (`docker-compose.yml`, `Dockerfile-db`, `Dockerfile-web`) are included in your repository. At the end of this assignment, mark your repository with the tag `ca4-part2`.
