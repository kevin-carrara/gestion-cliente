FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/TechnicalTestPinApp-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","spring-app.jar"]