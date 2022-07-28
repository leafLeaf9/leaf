FROM amazoncorretto:8u342-alpine3.14
MAINTAINER woxigousade
VOLUME /tmp
ADD target/gousade-1.1.2.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=xxx","-Dspring.profiles.active=prod","-jar","/app.jar"]