# Usa una imagen base de Java 17
FROM openjdk:17

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/microservices-users-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre el microservicio
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]