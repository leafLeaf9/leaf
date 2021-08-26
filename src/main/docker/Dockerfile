FROM java:8
MAINTAINER woxigousade
VOLUME /tmp
ADD spring-boot-gousade-1.1.2.war app.war
ENTRYPOINT ["java","-Djasypt.encryptor.password=xxx","-Dspring.profiles.active=prod","-jar","/app.war"]