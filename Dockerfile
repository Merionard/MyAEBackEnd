FROM openjdk:17-alpine
COPY target/myAEBackEnd-0.0.1-SNAPSHOT.jar myAEBackEnd.jar
ENTRYPOINT ["java","-jar","/myAEBackEnd.jar"]