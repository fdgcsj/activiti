FROM docker.io/maven:3.9.4-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -Daether.dependencyCollector.impl=bf
COPY src /app/src/
RUN mvn package

FROM docker.io/eclipse-temurin:8-jdk
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
