FROM maven:3.9.11-eclipse-temurin-17-alpine AS builder
WORKDIR /user-service
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests -B

FROM eclipse-temurin:17-jre-alpine-3.22
WORKDIR /opt/app
COPY --from=builder /user-service/target/*.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]

