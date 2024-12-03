package com.microservicios.app.cursos.services;

import com.microservicios.app.cursos.client.UserClient;
import com.microservicios.app.cursos.domain.entities.Course;
import com.microservicios.app.cursos.domain.entities.Enrollment;
import com.microservicios.app.cursos.domain.persistence.CourseRepository;
import com.microservicios.app.cursos.domain.persistence.EnrollmentRepository;
import com.microservicios.app.cursos.domain.services.IEnrollmentService;
import com.microservicios.app.cursos.dto.EnrollmentDto;
import com.microservicios.app.cursos.dto.PersonDto;
import com.microservicios.app.cursos.mapping.EnrollmentMapping;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrollmentServiceImplement implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapping mapping;

    private final UserClient userClient;


    public EnrollmentServiceImplement(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, EnrollmentMapping mapping, UserClient userClient) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.mapping = mapping;
        this.userClient = userClient;
    }

    public List<EnrollmentDto> listEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return mapping.modelList(enrollments);
    }


    @Override
    public EnrollmentDto enrollStudent(EnrollmentDto enrollmentDto) {
        // Verificar que el curso exista
        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Validar que el estudiante exista en el microservicio de Usuarios
        PersonDto student = userClient.getPersonById(enrollmentDto.getStudentId());
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        // Crear y guardar la matrícula
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudentId(enrollmentDto.getStudentId());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return mapping.modelDto(savedEnrollment);
    }

}
