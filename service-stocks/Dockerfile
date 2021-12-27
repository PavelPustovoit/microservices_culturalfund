FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/service-stock-0.0.1-SNAPSHOT.jar stock.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "stock.jar"]
