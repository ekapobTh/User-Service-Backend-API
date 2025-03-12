# -------------------------------------------
# 1) BUILD STAGE
# -------------------------------------------
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the Maven descriptor and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for speed)
RUN mvn clean package -DskipTests

# -------------------------------------------
# 2) RUN STAGE
# -------------------------------------------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/userservice-0.0.1-SNAPSHOT.jar /app/userservice.jar

# Expose the Spring Boot port (default 8080)
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java","-jar","/app/userservice.jar"]