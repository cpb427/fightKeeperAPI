FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

EXPOSE 80
# Copy the jar file
COPY /target/*.jar app.jar

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
