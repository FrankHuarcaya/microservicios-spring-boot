package com.microservicios.app.cursos.api;


import com.microservicios.app.cursos.domain.services.ICourseService;
import com.microservicios.app.cursos.dto.CourseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    private ICourseService courseService; // Mock del servicio

    @InjectMocks
    private CourseController courseController; // Inyectamos el mock en el controlador

    @Test
    void listCourses_ShouldReturnCourseList() {
        // Given: Datos válidos para registrar un curso
        List<CourseDto> mockCourses = List.of(
                new CourseDto(1L, "Course 1", "Description 1", 5, "A"),
                new CourseDto(2L, "Course 2", "Description 2", 4, "B")
        );
        // Simular comportamiento del servicio
        Mockito.when(courseService.listCourses()).thenReturn(mockCourses);

        // When: Llamar al método del controlador
        ResponseEntity<List<CourseDto>> response = courseController.listCourses();

        // Then: Validar que el curso fue creado correctamente
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals("Course 1", response.getBody().get(0).getName());
    }

    @Test
    void createCourse_ShouldReturnCreatedCourse() {
        // Datos simulados
        CourseDto newCourse = new CourseDto(null, "New Course", "New Description", 5, "A"); // Curso sin ID
        CourseDto savedCourse = new CourseDto(1L, "New Course", "New Description", 5, "A"); // Curso con ID generado

        // Simular el comportamiento del servicio
        Mockito.when(courseService.saveCourse(newCourse)).thenReturn(savedCourse);

        // Llamada al método del controlador
        ResponseEntity<CourseDto> response = courseController.createCourse(newCourse);

        // Validaciones
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Verificar el estado 201 Created
        Assertions.assertNotNull(response.getBody()); // Verificar que la respuesta no sea nula
        Assertions.assertEquals(1L, response.getBody().getId()); // Verificar que el ID sea correcto
        Assertions.assertEquals("New Course", response.getBody().getName()); // Verificar el nombre
    }

    @Test
    void updateCourse_ShouldReturnUpdatedCourse() {
        // Datos de entrada y simulación
        Long courseId = 1L;
        CourseDto updatedCourse = new CourseDto(courseId, "Updated Course", "Updated Description", 6, "B");
        Mockito.when(courseService.updateCourse(courseId, updatedCourse)).thenReturn(updatedCourse);

        // Llamada al método del controlador
        ResponseEntity<CourseDto> response = courseController.updateCourse(courseId, updatedCourse);

        // Validaciones
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // Verificar estado 200 OK
        Assertions.assertNotNull(response.getBody()); // Verificar que el cuerpo no sea nulo
        Assertions.assertEquals("Updated Course", response.getBody().getName()); // Validar el nombre
        Assertions.assertEquals(6, response.getBody().getHoursPerWeek()); // Validar horas por semana
    }

    @Test
    void deleteCourse_ShouldReturnNoContent() {
        // Datos de prueba
        Long courseId = 1L;

        // Simular el comportamiento del servicio
        Mockito.doNothing().when(courseService).deleteCourse(courseId);

        // Llamada al método del controlador
        ResponseEntity<Void> response = courseController.deleteCourse(courseId);

        // Validaciones
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Verificar estado 204 No Content
        Mockito.verify(courseService, Mockito.times(1)).deleteCourse(courseId); // Verificar que se llamó al servicio una vez
    }
}
