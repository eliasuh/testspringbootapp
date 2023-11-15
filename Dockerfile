FROM openjdk:17
WORKDIR /TESTSPRINGBOOTAPP
COPY . .
ADD target/studentdemo-1.0-SNAPSHOT.jar studentdemo.jar
CMD ["java","-jar","/studentdemo.jar"]