# Build stage
FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY orders/build.gradle.kts  ./
COPY --chown=gradle:gradle . .
RUN gradle dependencies --refresh-dependencies && \
    ./gradlew :orders:bootJar --no-daemon -x test

# Run stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/orders/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
