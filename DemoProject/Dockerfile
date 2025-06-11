# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy all project files
COPY . .

# Build the app (skipping tests for speed, optional)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy built JAR from builder
COPY --from=builder /target/DemoProject-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (optional, default is 8080)
EXPOSE 9000

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]