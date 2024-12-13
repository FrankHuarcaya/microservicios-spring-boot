package com.example.microservices.app.exams.dto;

import java.util.List;

public class ExamListDto {

    private String id; // ID único del examen
    private String title; // Título del examen
    private String description; // Descripción del examen
    private List<QuestionDto> questions; // Detalles completos de las preguntas asociadas
    private Long courseId; // ID del curso asociado
    private CourseDto courseDetails; // Detalles completos del curso desde el microservicio

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

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public CourseDto getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(CourseDto courseDetails) {
        this.courseDetails = courseDetails;
    }
}
