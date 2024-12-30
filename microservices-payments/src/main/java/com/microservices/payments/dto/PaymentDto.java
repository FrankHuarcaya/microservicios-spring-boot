package com.microservices.payments.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


public class PaymentDto {

    private String id;
    private Long studentId; // ID del estudiante
    private Long courseId; // ID del curso relacionado al pago
    private Double amount; // Monto del pago
    private String status; // Estado del pago: "PENDING", "COMPLETED", "FAILED"
    private Date paymentDate; // Fecha del pago

    private LocalDateTime createdAt; // Fecha de creación
    private LocalDateTime updatedAt; // Fecha de última actualización

    public PaymentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
