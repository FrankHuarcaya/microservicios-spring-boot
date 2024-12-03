package com.microservicios.app.cursos.client;


import com.microservicios.app.cursos.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservicio-usuarios") // Nombre del microservicio registrado en Eureka
public interface UserClient {
    @GetMapping("/api/v1/persons/{id}")
    PersonDto getPersonById(@PathVariable Long id);

    @GetMapping("/api/v1/persons")
    List<PersonDto> listPerson();

}
