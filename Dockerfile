# Estágio 1: Build da aplicação com Maven
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests -B

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

ARG BUILTJAR=target/backend-0.0.1-SNAPSHOT.jar

COPY --from=builder /app/${BUILTJAR} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]
