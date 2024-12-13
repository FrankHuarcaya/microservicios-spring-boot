package com.example.microservices.app.exams.client;

import com.example.microservices.app.exams.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicios-cursos")
public interface CourseClient {

    @GetMapping("/api/v1/courses/{id}")
    CourseDto getCourseById(@PathVariable("id") Long id);

}
