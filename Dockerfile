# Build
FROM maven:3.6.0-jdk-8-slim AS build
COPY analysis-service/src /analysis-service/src
COPY analysis-service/pom.xml /analysis-service
RUN mvn -f /analysis-service/pom.xml clean package

# Package stage
FROM openjdk:8-jre-slim
COPY --from=build /analysis-service/target/analysis-service-1.0-SNAPSHOT-shaded.jar /usr/local/lib/analysis-service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/analysis-service.jar"]
