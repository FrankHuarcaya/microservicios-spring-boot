package com.microservices.payments.client;

import com.microservices.payments.dto.PersonDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<PersonDto> getPersonById(Long id) {
        return webClient.get()
                .uri("/api/v1/persons/{id}", id)
                .retrieve()
                .bodyToMono(PersonDto.class)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El estudiante no existe")));
    }
}
