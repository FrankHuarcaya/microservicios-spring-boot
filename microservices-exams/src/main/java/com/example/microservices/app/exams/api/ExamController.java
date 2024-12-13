package com.example.microservices.app.exams.api;


import com.example.microservices.app.exams.domain.services.IExamService;
import com.example.microservices.app.exams.dto.ExamDto;
import com.example.microservices.app.exams.dto.ExamListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    private final IExamService examService;

    public ExamController(IExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.status(201).body(examService.createExam(examDto));
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> listExams() {
        return ResponseEntity.ok(examService.listExams());
    }

    // Endpoint para obtener un examen por ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable String id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    // Endpoint para eliminar un examen por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable String id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
