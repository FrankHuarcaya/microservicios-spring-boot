# Usa la imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY ./target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto del Config Server
EXPOSE 8888

# Comando para ejecutar el Config Server
ENTRYPOINT ["java", "-jar", "app.jar"]