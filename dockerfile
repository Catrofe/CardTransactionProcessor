# Build stage
FROM azul/zulu-openjdk-alpine:21 AS builder
WORKDIR /app
COPY . /app
RUN ./gradlew bootJar --no-daemon

# Runtime stage
FROM azul/zulu-openjdk-alpine:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]