
FROM openjdk:8               # FROM<image>
VOLUME /tmp                   # Temporary location to run
EXPOSE 8080                   # Provide port number
ADD target/SpringBootDockerTest-0.0.1-SNAPSHOT.jar SpringBootDockerTest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SpringBootDockerTest-0.0.1-SNAPSHOT.jar"]              # command to execute jar