FROM openjdk:22-jdk-slim AS build

WORKDIR /app

COPY . .

RUN ./mvnw package -DskipTests

FROM openjdk:22-jdk-slim

WORKDIR /app

COPY --from=build /app/target/KIII_APP_212044-0.0.1-SNAPSHOT.jar KIII_APP_212044.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "KIII_APP_212044.jar"]