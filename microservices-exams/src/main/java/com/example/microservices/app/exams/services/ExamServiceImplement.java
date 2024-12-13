package com.example.microservices.app.exams.services;

import com.example.microservices.app.exams.client.CourseClient;
import com.example.microservices.app.exams.domain.entities.Exam;
import com.example.microservices.app.exams.domain.persistence.ExamRepository;
import com.example.microservices.app.exams.domain.persistence.QuestionRepository;
import com.example.microservices.app.exams.domain.services.IExamService;
import com.example.microservices.app.exams.dto.ExamDto;
import com.example.microservices.app.exams.mapping.ExamMapping;
import com.example.microservices.app.exams.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExamServiceImplement implements IExamService {

    private final ExamRepository examRepository;
    private final ExamMapping mapping;
    private final CourseClient courseClient;

    private final QuestionRepository questionRepository;

    public ExamServiceImplement(ExamRepository examRepository, ExamMapping mapping, CourseClient courseClient, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.mapping = mapping;
        this.courseClient = courseClient;
        this.questionRepository = questionRepository;
    }

    @Override
    public ExamDto createExam(ExamDto examDto) {
        // Validar si el curso asociado existe
        try {
            courseClient.getCourseById(examDto.getCourseId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Course", examDto.getCourseId());
        }

        // Validar que todas las preguntas en el DTO existan en la base de datos
        List<String> validQuestionIds = examDto.getQuestions().stream()
                .filter(questionId -> questionRepository.existsById(questionId))
                .collect(Collectors.toList());

        if (validQuestionIds.size() != examDto.getQuestions().size()) {
            throw new RuntimeException("One or more questions do not exist.");
        }

        // Guardar el examen con los IDs válidos de las preguntas
        Exam exam = mapping.modelDto(examDto); // Mapear DTO a modelo
        exam.setQuestions(validQuestionIds); // Asignar las IDs válidas al examen
        Exam savedExam = examRepository.save(exam);

        // Retornar el examen creado como DTO
        return mapping.model(savedExam);
    }

    @Override
    public List<ExamDto> listExams() {
        List<Exam> exams = examRepository.findAll();
        return mapping.modelList(exams);
    }

    @Override
    public ExamDto getExamById(String id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam", id));
        return mapping.model(exam);    }

    @Override
    public void deleteExam(String id) {
        if (!examRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exam", id);
        }
        examRepository.deleteById(id);
    }
}
