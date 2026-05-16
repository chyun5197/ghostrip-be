FROM eclipse-temurin:21-jdk

COPY build/libs/ghostrip-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]