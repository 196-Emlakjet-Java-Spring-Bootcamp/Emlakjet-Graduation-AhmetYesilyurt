FROM openjdk:17
EXPOSE 8080
ADD /target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "cloud-gateway-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]