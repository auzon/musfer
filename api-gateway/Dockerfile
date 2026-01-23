FROM maven:3.9.11-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn package -DskipTests -B

FROM eclipse-temurin:17-jdk-alpine-3.23 AS development
WORKDIR /app
COPY --from=builder /app .
#
FROM eclipse-temurin:17-jre-alpine-3.22 AS production
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
