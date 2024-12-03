package com.microservicios.app.cursos.domain.services;

import com.microservicios.app.cursos.dto.EnrollmentDto;

import java.util.List;

public interface IEnrollmentService {


    List<EnrollmentDto> listEnrollments();
    EnrollmentDto enrollStudent(EnrollmentDto enrollmentDto);
}
