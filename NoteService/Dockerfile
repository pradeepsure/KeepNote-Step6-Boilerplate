FROM java:8-jre
WORKDIR usr/src
ADD ./target/NoteService-0.0.1-SNAPSHOT.jar /usr/src/NoteService-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/NoteService-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/NoteService-0.0.1-SNAPSHOT.jar"]