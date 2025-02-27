FROM openjdk:17

COPY ./target/ /usr/src/JavaApp/

COPY ./src/main/resources/ /usr/src/JavaApp/

WORKDIR /usr/src/JavaApp

ENV TZ Europe/Rome

CMD ["java","-cp",".:./Jdbc2Rest-1.jar","jdbc2rest.MainProcessing"]
