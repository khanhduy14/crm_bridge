FROM openjdk:8-jre
RUN apt-get update && apt-get install -y openjdk-8-jdk
RUN rm -rf /tmp/*
ADD	./target/crm-hubspot-direct.jar /tmp/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar","/tmp/crm-hubspot-direct.jar"]
