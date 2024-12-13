package com.example.microservices.app.exams.domain.services;

import com.example.microservices.app.exams.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {

    QuestionDto createQuestion(QuestionDto questionDto);

    List<QuestionDto> listQuestions();

    QuestionDto getQuestionById(String id);


    void deleteQuestion(String id);
}
