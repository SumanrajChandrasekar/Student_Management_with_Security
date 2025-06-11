# Stage 1: Build the Spring Boot app
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy everything from the root of the repo (where pom.xml is)
COPY . .

# Run Maven build
RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
