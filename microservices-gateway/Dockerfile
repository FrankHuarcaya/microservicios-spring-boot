# Usa la imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/microservices-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto del API Gateway
EXPOSE 8090

# Comando para ejecutar el API Gateway
ENTRYPOINT ["java", "-jar", "app.jar"]