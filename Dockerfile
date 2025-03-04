FROM openjdk:17-jdk-slim
COPY target/microservice*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]