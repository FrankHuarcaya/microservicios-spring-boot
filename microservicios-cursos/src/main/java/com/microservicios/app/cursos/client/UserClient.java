package com.microservicios.app.cursos.client;


import com.microservicios.app.cursos.dto.PersonDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "microservicio-usuarios") // Nombre del microservicio registrado en Eureka
public interface UserClient {
    @GetMapping("/api/v1/persons/{id}")
    @CircuitBreaker(name = "microservices-cursos", fallbackMethod = "fallbackGetPersonById")
    PersonDto getPersonById(@PathVariable Long id);

    @GetMapping("/api/v1/persons")
    @CircuitBreaker(name = "microservices-cursos", fallbackMethod = "fallbackListPersons")
    List<PersonDto> listPerson();

    // Métodos fallback
        default PersonDto fallbackGetPersonById(Long id, Throwable throwable) {
        System.out.println("Fallback for getPersonById. Error: " + throwable.getMessage());
        return new PersonDto(); // Devuelve un objeto vacío o datos predeterminados
    }

    default List<PersonDto> fallbackListPersons(Throwable throwable) {
        System.out.println("Fallback for listPerson. Error: " + throwable.getMessage());
        return new ArrayList<>(); // Devuelve una lista vacía o predeterminada
    }
}
