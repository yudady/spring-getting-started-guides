FROM eclipse-temurin:11-jre as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:11-jre
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# $ docker build -t yudady/devops-01-maven --progress=plain --build-arg JAR_FILE=target/devops-01-maven-0.0.1-SNAPSHOT.jar .
# $ docker build -t yudady/devops-01-maven --build-arg JAR_FILE=target/devops-01-maven-0.0.1-SNAPSHOT.jar .