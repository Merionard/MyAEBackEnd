FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/myAEBackEnd-0.0.1-SNAPSHOT.jar myAEBackEnd.jar
ENTRYPOINT ["java","-jar","/myAEBackEnd.jar"]