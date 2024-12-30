package com.microservices.payments.client;

import com.microservices.payments.dto.CourseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CourseService {

    private final WebClient webClient;

    public CourseService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<CourseDto> getCourseById(Long id) {
        return webClient.get()
                .uri("/api/v1/courses/{id}", id)
                .retrieve()
                .bodyToMono(CourseDto.class)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El curso no existe")));
    }
}
