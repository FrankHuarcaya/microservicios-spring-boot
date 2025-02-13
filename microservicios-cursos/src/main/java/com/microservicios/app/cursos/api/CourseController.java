package com.microservicios.app.cursos.api;


import com.microservicios.app.cursos.domain.services.ICourseService;
import com.microservicios.app.cursos.dto.CourseDto;
import com.microservicios.app.cursos.shared.domain.model.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@Tag(name = "Courses API", description = "Manage courses in the system") // Descripción del controlador
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "List all courses", description = "Retrieve a list of all available courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CourseDto>>> listCourses() {
        return courseService.listCourses();
    }
    @Operation(summary = "Create a new course", description = "Register a new course in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto savedCourse = courseService.saveCourse(courseDto);
        return ResponseEntity.status(201).body(savedCourse);
    }

    @Operation(summary = "Update a course", description = "Update the details of an existing course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
        return ResponseEntity.ok(updatedCourse);
    }


    @Operation(summary = "Retrieve a course by ID", description = "Retrieve the details of a specific course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }


    @Operation(summary = "Delete a course", description = "Remove a course from the system by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
