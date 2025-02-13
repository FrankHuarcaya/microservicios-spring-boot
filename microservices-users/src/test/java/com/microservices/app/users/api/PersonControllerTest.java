package com.microservices.app.users.api;


import com.microservices.app.users.domain.services.IPersonServices;
import com.microservices.app.users.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IPersonServices personService;

    @InjectMocks
    private PersonController personController;

    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setFirstName("John");
        personDto.setLastName("Doe");
        personDto.setEmail("john@example.com");
    }

    @Test
    void testListPersons() throws Exception {
        List<PersonDto> persons = Arrays.asList(personDto);
        when(personService.listPerson()).thenReturn(persons);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testCreatePerson() throws Exception {
        when(personService.registerPerson(any(PersonDto.class))).thenReturn(personDto);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(personDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        PersonDto updatedPersonDto = new PersonDto();
        updatedPersonDto.setId(1L);
        updatedPersonDto.setFirstName("Jane"); // Nombre cambiado
        updatedPersonDto.setLastName("Doe");
        updatedPersonDto.setEmail("jane@example.com");

        when(personService.updatePerson(eq(1L), any(PersonDto.class))).thenReturn(updatedPersonDto);

        mockMvc.perform(put("/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedPersonDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane")) // Verifica que el nombre se actualizó
                .andExpect(jsonPath("$.email").value("jane@example.com")); // Verifica el email
    }

    @Test
    void testGetPersonById() throws Exception {
        when(personService.retrievePerson(1L)).thenReturn(personDto);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testDeletePerson() throws Exception {
        doNothing().when(personService).deletePerson(1L);

        mockMvc.perform(delete("/persons/1"))
                .andExpect(status().isNoContent());
    }

}
