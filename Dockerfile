FROM openjdk:18.0-jdk-slim
EXPOSE 8082
COPY target/*.jar redis-cluster.jar
ENTRYPOINT ["java","-jar","/redis-cluster.jar"]