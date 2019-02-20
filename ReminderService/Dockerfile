FROM java:8-jre
WORKDIR usr/src
ADD ./target/ReminderService-0.0.1-SNAPSHOT.jar /usr/src/ReminderService-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/ReminderService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/ReminderService-0.0.1-SNAPSHOT.jar"]