package com.example.microservices.app.exams.mapping;

import com.example.microservices.app.exams.domain.entities.Exam;
import com.example.microservices.app.exams.dto.ExamDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExamMapping {

    private final ModelMapper modelMapper;

    public ExamMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Exam modelDto(ExamDto examDto) {
        return this.modelMapper.map(examDto, Exam.class);
    }

    public ExamDto model(Exam exam) {
        return this.modelMapper.map(exam, ExamDto.class);
    }

    public List<ExamDto> modelList(List<Exam> examList) {
        return examList.stream()
                .map(item -> modelMapper.map(item, ExamDto.class))
                .collect(Collectors.toList());
    }
}
