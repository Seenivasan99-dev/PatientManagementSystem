FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:21-jdk AS Runner
WORKDIR /app
COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 4004
ENTRYPOINT ["java","-jar","app.jar"]