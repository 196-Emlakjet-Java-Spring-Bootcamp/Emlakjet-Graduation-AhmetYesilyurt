FROM openjdk:17
EXPOSE 8082
ADD /target/banner-service-0.0.1-SNAPSHOT.jar banner-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "banner-service-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]