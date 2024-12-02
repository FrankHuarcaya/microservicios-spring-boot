package com.microservices.app.users.domain.services;

import com.microservices.app.users.dto.PersonDto;

import java.util.List;

public interface IPersonServices {

    List<PersonDto> listPerson();

    PersonDto registerPerson(PersonDto personDto);

    PersonDto updatePerson(Long id, PersonDto personDto);

    PersonDto retrievePerson(Long id);

    Void deletePerson(Long id);
}
