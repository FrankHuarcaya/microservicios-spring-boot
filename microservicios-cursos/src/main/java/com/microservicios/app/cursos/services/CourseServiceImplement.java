package com.microservicios.app.cursos.services;

import com.microservicios.app.cursos.domain.entities.Course;
import com.microservicios.app.cursos.domain.persistence.CourseRepository;
import com.microservicios.app.cursos.domain.services.ICourseService;
import com.microservicios.app.cursos.dto.CourseDto;
import com.microservicios.app.cursos.mapping.CourseMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class CourseServiceImplement implements ICourseService {


    private final CourseRepository courseRepository;
    private final CourseMapping mapping;

    public CourseServiceImplement(CourseRepository courseRepository, CourseMapping mapping) {
        this.courseRepository = courseRepository;
        this.mapping = mapping;
    }


    @Override
    public List<CourseDto> listCourses() {

        return mapping.modelList(courseRepository.findAll());
    }

    @Override
    public CourseDto saveCourse(CourseDto cursoDto) {
        // Mapea el DTO a la entidad
        Course courseSave = mapping.model(cursoDto);
        // Guarda la entidad y obtén la versión persistida con valores actualizados
        Course savedCourse = courseRepository.save(courseSave);
        return mapping.modelDto(courseSave);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setName(courseDto.getName());
            course.setDescription(courseDto.getDescription());
            course.setHoursPerWeek(courseDto.getHoursPerWeek());
            course.setSection(courseDto.getSection());
            Course updatedCourse = courseRepository.save(course);
            return mapping.modelDto(updatedCourse);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapping.modelDto(course);
    }


    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
