package com.example.microservices.app.exams.domain.persistence;

import com.example.microservices.app.exams.domain.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface QuestionRepository extends MongoRepository<Question,String> {
}
