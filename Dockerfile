FROM java:8

ADD target/my-app-1.0-SNAPSHOT.jar my-app.jar

ENTRYPOINT ["java", "-jar", "./my-app.jar"]
