FROM openjdk:19
ARG JAR_FILE=build/libs/computerDept_backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]