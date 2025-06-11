# Stage 1: Build the Spring Boot app
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy all source code
COPY src ./src

# Package the application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# Stage 2: Create minimal runtime image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy JAR file from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 9000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
