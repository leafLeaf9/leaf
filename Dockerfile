FROM java:8
MAINTAINER woxigousade
VOLUME /tmp
ADD target/gousade-1.1.2.jar /app/app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=xxx","-Dspring.profiles.active=prod","-jar","/app.jar"]