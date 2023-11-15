FROM openjdk:8
EXPOSE 8080
ADD target/student-1.0-SNAPSHOT.jar studentapp.jar
CMD ["java","-jar","/studentapp.jar"]