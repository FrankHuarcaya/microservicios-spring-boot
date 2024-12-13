package com.example.microservices.app.exams.domain.services;

import com.example.microservices.app.exams.dto.ExamDto;
import com.example.microservices.app.exams.dto.ExamListDto;

import java.util.List;

public interface IExamService {

    ExamDto createExam(ExamDto examDto); // Crear un examen

    List<ExamDto> listExams(); // Listar todos los exámenes

    ExamDto getExamById(String id); // Obtener un examen por ID

    void deleteExam(String id); // Eliminar un examen por ID
}
