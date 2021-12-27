FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/service-feedback-0.0.1-SNAPSHOT.jar feedback.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "feedback.jar"]