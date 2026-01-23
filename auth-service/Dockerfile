FROM maven:3.9.11-eclipse-temurin-17-alpine AS builder
WORKDIR /auth-service
COPY pom.xml .
RUN mvn package -DskipTests -B || true
COPY . .
RUN mvn package -DskipTests -B

FROM eclipse-temurin:17-jdk-alpine-3.23 AS development
WORKDIR /opt/app
COPY --from=builder /auth-service /opt/app

FROM eclipse-temurin:17-jre-alpine-3.22 AS production
WORKDIR /opt/app
COPY --from=builder /auth-service/target/*.jar auth-service.jar
CMD ["java", "-jar", "auth-service.jar"]

