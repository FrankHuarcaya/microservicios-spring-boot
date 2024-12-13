package com.example.microservices.app.exams.mapping;

import com.example.microservices.app.exams.domain.entities.Question;
import com.example.microservices.app.exams.dto.QuestionDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapping {
    private final ModelMapper modelMapper;

    public QuestionMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Question modelDto(QuestionDto questionDto){
        return this.modelMapper.map(questionDto,Question.class);
    }

    public QuestionDto model(Question question){
        return this.modelMapper.map(question,QuestionDto.class);
    }

    public List<QuestionDto> modelList(List<Question> questionList){
        return questionList.stream().map(item->modelMapper.map(item,QuestionDto.class))
                .collect(Collectors.toList());
    }

}
