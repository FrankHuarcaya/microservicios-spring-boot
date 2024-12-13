package com.example.microservices.app.exams.dto;

import lombok.*;


public class QuestionDto {

    private String id; // ID único de la pregunta
    private String text; // Texto de la pregunta
    private String correctAnswer; // Respuesta correcta
    private String[] options; // Opciones disponibles

    public QuestionDto(String id, String text, String correctAnswer, String[] options) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }
    public QuestionDto(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
