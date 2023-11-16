FROM openjdk:17
COPY . .
ADD target/studentdemo.jar studentdemo.jar
CMD ["java","-jar","studentdemo.jar"]