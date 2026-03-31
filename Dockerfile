FROM eclipse-temurin:11-jre

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY /target/*.jar app.jar

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
