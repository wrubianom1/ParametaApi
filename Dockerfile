FROM openjdk:8-jre-alpine
RUN apk -v --update add \
    python \
    py-pip \
    groff \
    less \
    mailcap \
    curl \
    && \
    pip install python-magic && \
    apk -v --purge del py-pip && \
    rm /var/cache/apk/*


COPY *-web/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
MAINTAINER wrubianom1@gmail.com