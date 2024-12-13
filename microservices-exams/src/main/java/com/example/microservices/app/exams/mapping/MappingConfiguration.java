package com.example.microservices.app.exams.mapping;


import com.example.microservices.app.exams.domain.entities.Exam;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("examMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public  QuestionMapping questionMapping(ModelMapper modelMapper){
        return new QuestionMapping (modelMapper);
    }

    @Bean
    public  ExamMapping examMapping(ModelMapper modelMapper){
        return new ExamMapping(modelMapper);
    }
}
