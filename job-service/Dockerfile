# Use an official OpenJDK runtime as a parent image for Java 21
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/*.jar app.jar

# Expose port 8090 to the outside world
EXPOSE 8090

# Command to run the Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
