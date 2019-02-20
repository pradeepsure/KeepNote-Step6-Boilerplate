FROM java:8-jre
WORKDIR usr/src
ADD ./target/CategoryService-0.0.1-SNAPSHOT.jar /usr/src/CategoryService-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/CategoryService-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/CategoryService-0.0.1-SNAPSHOT.jar"]