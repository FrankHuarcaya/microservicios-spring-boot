package com.example.microservices.app.exams.api;

import com.example.microservices.app.exams.domain.services.IQuestionService;
import com.example.microservices.app.exams.dto.QuestionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final IQuestionService questionService;

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.status(201).body(questionService.createQuestion(questionDto));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> listQuestions() {
        return ResponseEntity.ok(questionService.listQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable String id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
