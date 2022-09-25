FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



# docker build -t yudady/01-gs-rest-service .
#docker run -p 8080:8080 yudady/01-gs-rest-service