package com.microservices.app.users.services;


import com.microservices.app.users.domain.entites.Person;
import com.microservices.app.users.domain.persistence.PersonRepository;
import com.microservices.app.users.dto.PersonDto;
import com.microservices.app.users.mapping.PersonMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapping mapping;

    @InjectMocks
    private PersonServiceImplement personService;

    private Person person;
    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john@example.com");

        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setFirstName("John");
        personDto.setLastName("Doe");
        personDto.setEmail("john@example.com");
    }

    @Test
    void testListPersons() {
        when(personRepository.findAll()).thenReturn(Arrays.asList(person));
        when(mapping.modelList(anyList())).thenReturn(Arrays.asList(personDto));

        List<PersonDto> persons = personService.listPerson();

        assertNotNull(persons);
        assertEquals(1, persons.size());
        assertEquals("John", persons.get(0).getFirstName());
    }

    @Test
    void testRegisterPerson() {
        when(mapping.model(personDto)).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(mapping.modelDto(person)).thenReturn(personDto);

        PersonDto savedPerson = personService.registerPerson(personDto);

        assertNotNull(savedPerson);
        assertEquals("John", savedPerson.getFirstName());
    }

    @Test
    void testRetrievePerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(mapping.modelDto(person)).thenReturn(personDto);

        PersonDto retrievedPerson = personService.retrievePerson(1L);

        assertNotNull(retrievedPerson);
        assertEquals("John", retrievedPerson.getFirstName());
    }

    @Test
    void testDeletePerson() {
        when(personRepository.existsById(1L)).thenReturn(true);
        doNothing().when(personRepository).deleteById(1L);

        assertDoesNotThrow(() -> personService.deletePerson(1L));
        verify(personRepository, times(1)).deleteById(1L);
    }
}
