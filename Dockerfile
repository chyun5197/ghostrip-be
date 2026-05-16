FROM eclipse-temurin:21-jdk

COPY build/libs/ghostream-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]