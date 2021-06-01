FROM openjdk:11-jre-slim-buster
COPY ./target/reading-app-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch reading-app-0.0.1-SNAPSHOT.jar'
ARG JAR_FILE=target/reading-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","reading-app-0.0.1-SNAPSHOT.jar"]