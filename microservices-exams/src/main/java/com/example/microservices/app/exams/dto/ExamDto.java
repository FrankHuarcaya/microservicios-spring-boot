package com.example.microservices.app.exams.dto;

import java.util.List;

public class ExamDto {

    private String id; // ID único del examen
    private String title; // Título del examen
    private String description; // Descripción del examen
    private List<String> questions; // Lista de preguntas asociadas
    private Long courseId; // ID del curso asociado



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
