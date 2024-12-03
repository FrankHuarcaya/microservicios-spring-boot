package com.microservicios.app.cursos.dto;

import lombok.Data;

@Data
public class EnrollmentDto {

    private Long id;        // ID de la matrícula
    private Long courseId;  // ID del curso al que pertenece
    private Long studentId; // ID del estudiante que se matricula

}
