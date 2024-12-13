package com.example.microservices.app.exams.domain.persistence;

import com.example.microservices.app.exams.domain.entities.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam,String> {
}
