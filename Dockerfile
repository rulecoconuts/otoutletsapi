FROM openjdk:12-alpine
# maven:3.8.4-openjdk-17-slim
WORKDIR /outletsapi
COPY . .
# RUN bash mvnw clean install -DskipTests
EXPOSE 8080
ENTRYPOINT [ "/bin/sh", "runServer.sh"]