package com.microservicios.app.cursos.domain.services;

import com.microservicios.app.cursos.dto.CourseDto;
import com.microservicios.app.cursos.shared.domain.model.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService {

    ResponseEntity<ApiResponseDto<List<CourseDto>>> listCourses();
    CourseDto saveCourse(CourseDto cursoDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);

    CourseDto getCourseById(Long id);
    void deleteCourse(Long id);



}
