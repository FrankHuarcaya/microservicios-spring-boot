package com.microservices.app.users.services;

import com.microservices.app.users.domain.entites.Person;
import com.microservices.app.users.domain.persistence.PersonRepository;
import com.microservices.app.users.domain.services.IPersonServices;
import com.microservices.app.users.dto.PersonDto;
import com.microservices.app.users.mapping.PersonMapping;
import com.microservices.app.users.shared.domain.model.ApiResponse;
import com.microservices.app.users.shared.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplement implements IPersonServices {


    private final PersonRepository personRepository;

    private final PersonMapping mapping;

    public PersonServiceImplement(PersonRepository personRepository, PersonMapping mapping) {
        this.personRepository = personRepository;
        this.mapping = mapping;
    }

    @Override
    public ResponseEntity<ApiResponse<List<PersonDto>>> listPerson() {
        List<PersonDto> persons = mapping.modelList(personRepository.findAll());

        if (persons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse<>(204, "No se encontraron registros de personas.", persons));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "Personas encontradas con éxito", persons));
    }

    @Override
    public PersonDto registerPerson(PersonDto personDto) {
        Person person = mapping.model(personDto);
        Person personSave = personRepository.save(person);
        return mapping.modelDto(personSave);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (!optionalPerson.isPresent()) {
            throw new ResourceNotFoundException("Person",id);
        }
        Person person = optionalPerson.get();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setRole(personDto.getRole());

        Person updatedPerson = personRepository.save(person);

        return mapping.modelDto(updatedPerson);
    }

    @Override
    public PersonDto retrievePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (!optionalPerson.isPresent()) {
            throw new ResourceNotFoundException("Person",id);
        }

        return mapping.modelDto(optionalPerson.get());
    }

    @Override
    public Void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with id: " + id);
        }
        personRepository.deleteById(id);
        return null;
    }
}
