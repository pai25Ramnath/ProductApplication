FROM openjdk:8
EXPOSE 8080
LABEL maintainer="javaapplication"
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]