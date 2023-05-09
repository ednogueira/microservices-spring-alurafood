FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
RUN apk update && apk add --no-cache bash
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh
COPY --from=build /app/target/pagamentos-0.0.1-SNAPSHOT.jar /usr/local/bin/app.jar
WORKDIR /usr/local/bin/
ENTRYPOINT ["/bin/sh", "/usr/local/bin/entrypoint.sh"]
