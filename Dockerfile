FROM openjdk:17-alpine
COPY target/myAEBackEnd-0.0.1-SNAPSHOT.jar myAEBackEnd.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myAEBackEnd.jar"]