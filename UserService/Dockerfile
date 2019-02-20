FROM java:8-jre
WORKDIR usr/src
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/src/UserService-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/UserService-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/UserService-0.0.1-SNAPSHOT.jar"]