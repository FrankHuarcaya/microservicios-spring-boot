package com.microservicios.app.cursos.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonDto {

    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;
    @Email(message="Ingrese un correo valido")
    private String email;

    private String phoneNumber;
    @NotNull(message = "El rol no puede ser nulo")
    private String role;
}
