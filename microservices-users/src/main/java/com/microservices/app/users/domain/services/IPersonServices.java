package com.microservices.app.users.domain.services;

import com.microservices.app.users.dto.PersonDto;
import com.microservices.app.users.shared.domain.model.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonServices {

    ResponseEntity<ApiResponse<List<PersonDto>>> listPerson();

    PersonDto registerPerson(PersonDto personDto);

    PersonDto updatePerson(Long id, PersonDto personDto);

    PersonDto retrievePerson(Long id);

    Void deletePerson(Long id);
}
