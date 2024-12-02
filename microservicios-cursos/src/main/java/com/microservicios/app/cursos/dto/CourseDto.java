package com.microservicios.app.cursos.dto;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class CourseDto {

    private Long id;

    private String name;
    private String description;
    private int hoursPerWeek;
    private String section;
}
