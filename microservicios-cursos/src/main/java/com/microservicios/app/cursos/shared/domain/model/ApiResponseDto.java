package com.microservicios.app.cursos.shared.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ApiResponseDto<T>{
    private MetaData meta;
    private T datos;

    public ApiResponseDto(int status, String message, T datos, int totalRegistros) {
        this.meta = new MetaData(status, message, totalRegistros);
        this.datos = datos;
    }

    public MetaData getMeta() {
        return meta;
    }

    public T getDatos() {
        return datos;
    }

    // Clase anidada para la metadata
    static class MetaData {
        private LocalDateTime timestamp;
        private int status;
        private List<Message> mensajes;
        private int totalRegistros;
        private String idTransaccion;

        public MetaData(int status, String message, int totalRegistros) {
            this.timestamp = LocalDateTime.now();
            this.status = status;
            this.mensajes = List.of(new Message("0", message, "info"));
            this.totalRegistros = totalRegistros;
            this.idTransaccion = UUID.randomUUID().toString(); // Genera un ID único por respuesta
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public int getStatus() { return status; }
        public List<Message> getMensajes() { return mensajes; }
        public int getTotalRegistros() { return totalRegistros; }
        public String getIdTransaccion() { return idTransaccion; }
    }

    // Clase anidada para mensajes
    static class Message {
        private String codigo;
        private String mensaje;
        private String tipo;

        public Message(String codigo, String mensaje, String tipo) {
            this.codigo = codigo;
            this.mensaje = mensaje;
            this.tipo = tipo;
        }

        public String getCodigo() { return codigo; }
        public String getMensaje() { return mensaje; }
        public String getTipo() { return tipo; }
    }
}
