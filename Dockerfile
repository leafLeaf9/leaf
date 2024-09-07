FROM amazoncorretto:8u342-alpine3.14
MAINTAINER woxigousade
VOLUME /tmp
ADD target/leaf-2.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Djasypt.encryptor.password=xxx","-Dspring.profiles.active=prod","-jar","/app.jar"]