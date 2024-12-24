package com.microservicios.app.cursos.services;

import com.microservicios.app.cursos.client.UserClient;
import com.microservicios.app.cursos.domain.entities.Course;
import com.microservicios.app.cursos.domain.entities.Enrollment;
import com.microservicios.app.cursos.domain.persistence.CourseRepository;
import com.microservicios.app.cursos.domain.persistence.EnrollmentRepository;
import com.microservicios.app.cursos.domain.services.IEnrollmentService;
import com.microservicios.app.cursos.dto.CourseDto;
import com.microservicios.app.cursos.dto.EnrollmentDto;
import com.microservicios.app.cursos.dto.PersonDto;
import com.microservicios.app.cursos.mapping.CourseMapping;
import com.microservicios.app.cursos.mapping.EnrollmentMapping;
import com.microservicios.app.cursos.shared.exception.ResourceNotFoundException;
import com.microservicios.app.cursos.shared.exception.ServiceUnavailableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EnrollmentServiceImplement implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapping mapping;

    private final CourseMapping courseMapping;

    private final UserClient userClient;


    public EnrollmentServiceImplement(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, EnrollmentMapping mapping, CourseMapping courseMapping, UserClient userClient) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.mapping = mapping;
        this.courseMapping = courseMapping;
        this.userClient = userClient;
    }

    public List<EnrollmentDto> listEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        // Mapear y enriquecer con datos externos
        return enrollments.stream().map(enrollment -> {
            EnrollmentDto dto = mapping.modelDto(enrollment);

            // Verificar que el curso exista en la base de datos
            Course course = courseRepository.findById(enrollment.getCourse().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course", enrollment.getCourse().getId()));
            CourseDto courseDto = courseMapping.modelDto(course); // Mapear la entidad Course a CourseDto
            dto.setCourse(courseDto);

            // Llamar al microservicio de Usuarios
            PersonDto studentDto = userClient.getPersonById(enrollment.getStudentId());
            if (studentDto == null || studentDto.getId() == null) {
                // Lanzar excepción si no se pudo obtener el usuario
                throw new ServiceUnavailableException("El servicio de usuarios no está disponible.");
            }

            dto.setStudent(studentDto);

            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public EnrollmentDto enrollStudent(EnrollmentDto enrollmentDto) {
        // Verificar que el curso exista
        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new  ResourceNotFoundException("Course",enrollmentDto.getCourseId()));

        // Validar que el estudiante exista en el microservicio de Usuarios
        PersonDto student = userClient.getPersonById(enrollmentDto.getStudentId());
        if (student == null) {
            throw new ResourceNotFoundException("Student",enrollmentDto.getStudentId());
        }

        // Crear y guardar la matrícula
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudentId(enrollmentDto.getStudentId());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return mapping.modelDto(savedEnrollment);
    }

}
