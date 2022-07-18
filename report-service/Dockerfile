FROM openjdk:17
EXPOSE 8083
ADD /target/report-service-0.0.1-SNAPSHOT.jar report-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "report-service-0.0.1-SNAPSHOT.jar","--spring.pofiles.active=prod"]