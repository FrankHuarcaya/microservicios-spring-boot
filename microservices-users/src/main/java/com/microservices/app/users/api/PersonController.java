package com.microservices.app.users.api;


import com.microservices.app.users.domain.services.IPersonServices;
import com.microservices.app.users.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final IPersonServices personService;

    public PersonController(IPersonServices personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> listPersons() {
        List<PersonDto> persons = personService.listPerson();
        return ResponseEntity.ok(persons); // Devuelve un 200 OK con la lista de personas
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        PersonDto savedPerson = personService.registerPerson(personDto);
        return ResponseEntity.status(201).body(savedPerson); // Devuelve un 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        PersonDto updatedPerson = personService.updatePerson(id, personDto);
        return ResponseEntity.ok(updatedPerson); // Devuelve un 200 OK con la persona actualizada
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        PersonDto person = personService.retrievePerson(id);
        return ResponseEntity.ok(person); // Devuelve un 200 OK con la persona encontrada
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
            personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
