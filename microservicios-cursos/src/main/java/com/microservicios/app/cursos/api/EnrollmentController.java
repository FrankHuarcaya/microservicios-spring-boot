package com.microservicios.app.cursos.api;


import com.microservicios.app.cursos.domain.services.IEnrollmentService;
import com.microservicios.app.cursos.dto.EnrollmentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private final IEnrollmentService enrollmentService;

    public EnrollmentController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> listEnrollments() {
        List<EnrollmentDto> enrollments = enrollmentService.listEnrollments();
        return ResponseEntity.ok(enrollments);
    }
    @PostMapping
    public ResponseEntity<EnrollmentDto> enrollStudent(@RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto enrollment = enrollmentService.enrollStudent(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }
}
