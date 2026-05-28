FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd pom.xml ./
RUN ./mvnw dependency:go-offline -q

COPY src/ src/
RUN ./mvnw package -DskipTests -q

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring && \
    mkdir -p /app/uploads && \
    chown -R spring:spring /app/uploads

COPY --from=build /app/target/*.jar app.jar

USER spring

VOLUME /app/uploads

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
