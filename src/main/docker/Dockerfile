FROM openjdk:8-jdk-alpine
MAINTAINER woxigousade
VOLUME /tmp
ADD spring-boot-gousade-1.1.2.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]