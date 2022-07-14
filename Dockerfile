FROM gradle:7.4.2-jdk17-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app/awaken-user-service.jar
ENTRYPOINT ["java","-jar","/app/awaken-user-service.jar"]
