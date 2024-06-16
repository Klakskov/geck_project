# Use an official Maven image to build the application with Eclipse Temurin 21
FROM  jelastic/maven:3.9.5-openjdk-21 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code to the working directory
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

# Use an official Eclipse Temurin image for Java 21 to run the application
FROM eclipse-temurin:21-jdk

# Set the working directory in the container
WORKDIR /app

# Create the images directory in the final image
RUN mkdir -p src/main/resources/images
VOLUME /app/src/main/resources/images/

# Copy the built application from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]