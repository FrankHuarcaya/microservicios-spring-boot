# Usa una imagen base de Java 17
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/microservices-payments-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre el servicio
EXPOSE 8084

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]