FROM openjdk:17
COPY . .
ADD target/studentdemo-1.0-SNAPSHOT.jar studentdemo.jar
CMD ["java","-jar","/studentdemo.jar"]