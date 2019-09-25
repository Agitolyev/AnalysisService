# Build
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /analysis-service/src
COPY pom.xml /analysis-service
RUN mvn -f /analysis-service/pom.xml clean package

# Package stage
FROM openjdk:8-jre-slim
COPY --from=build /analysis-service/target/analysis-service-1.0-SNAPSHOT-shaded.jar /usr/local/lib/analysis-service.jar
COPY entrypoint.sh /entrypoint/
ENTRYPOINT ["sh", "/entrypoint/entrypoint.sh"]
