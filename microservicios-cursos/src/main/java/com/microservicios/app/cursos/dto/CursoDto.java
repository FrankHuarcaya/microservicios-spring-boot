package com.microservicios.app.cursos.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CursoDto {

    private Long id;

    private String nombre;

}
