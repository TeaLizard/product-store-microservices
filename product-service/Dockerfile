FROM eclipse-temurin:21-jdk AS build

WORKDIR /workspace


COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .


RUN chmod +x mvnw

RUN ./mvnw -q -DskipTests dependency:go-offline

COPY ../shared-contracts /workspace/shared-contracts
RUN cd shared-contracts && ../mvnw -q -DskipTests clean install

COPY src ./src

RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -ms /bin/bash spring
USER spring

COPY --from=build /workspace/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
