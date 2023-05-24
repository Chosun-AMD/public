# Base image
FROM openjdk:11-jdk

# Set the working directory
WORKDIR /app

# Install additional tools
RUN apt update -y && apt install -y vim curl net-tools dnsutils

# Copy the JAR file to the container
COPY ./build/libs/AMD_Project-0.0.1-SNAPSHOT.jar app.jar

# Set the entrypoint command
CMD ["java", "-jar", "-Dspring.thymeleaf.prefix=classpath:/templates/", "/app/app.jar"]
