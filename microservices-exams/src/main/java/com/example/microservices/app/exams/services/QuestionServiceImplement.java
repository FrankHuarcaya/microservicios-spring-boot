package com.example.microservices.app.exams.services;

import com.example.microservices.app.exams.domain.entities.Question;
import com.example.microservices.app.exams.domain.persistence.QuestionRepository;
import com.example.microservices.app.exams.domain.services.IQuestionService;
import com.example.microservices.app.exams.dto.QuestionDto;
import com.example.microservices.app.exams.mapping.QuestionMapping;
import com.example.microservices.app.exams.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImplement implements IQuestionService {

    private static final String Entity="Question";

    private final QuestionMapping mapping;
    private final QuestionRepository questionRepository;

    public QuestionServiceImplement(QuestionMapping mapping, QuestionRepository questionRepository) {
        this.mapping = mapping;
        this.questionRepository = questionRepository;
    }


    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = mapping.modelDto(questionDto);
        Question savedQuestion = questionRepository.save(question);
        return mapping.model(savedQuestion);
    }

    @Override
    public List<QuestionDto> listQuestions() {
        return mapping.modelList(questionRepository.findAll());
    }

    @Override
    public QuestionDto getQuestionById(String id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        return mapping.model(question);
    }



    @Override
    public void deleteQuestion(String id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

}
