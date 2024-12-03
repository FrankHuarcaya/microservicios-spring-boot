package com.microservicios.app.cursos.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;

    private String name;
    private String description;
    private int hoursPerWeek;
    private String section;
}
