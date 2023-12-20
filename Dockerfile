FROM openjdk:8
EXPOSE 8085
ADD target/e-banking-integration.jar e-banking-integration.jar
ENTRYPOINT ["java","-jar","/e-banking-integration.jar"]