
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} crm.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/crm.jar"]