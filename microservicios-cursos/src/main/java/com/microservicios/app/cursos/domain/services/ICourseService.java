package com.microservicios.app.cursos.domain.services;

import com.microservicios.app.cursos.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseDto> listCourses();

    CourseDto saveCourse(CourseDto cursoDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);

    CourseDto getCourseById(Long id);
    void deleteCourse(Long id);



}
