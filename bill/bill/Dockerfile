
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} bill.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/bill.jar"]