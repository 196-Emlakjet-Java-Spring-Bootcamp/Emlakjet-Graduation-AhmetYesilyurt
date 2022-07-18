FROM openjdk:17
EXPOSE 8081
ADD /target/emlakjet-0.0.1-SNAPSHOT.jar emlakjet-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "emlakjet-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]