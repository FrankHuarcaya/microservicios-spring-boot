package com.microservices.app.users.domain.services;

import com.microservices.app.users.domain.entites.Alumno;

import java.util.Optional;

public interface IAlumnoServices {
    public Iterable<Alumno> list();

    public Optional<Alumno> findById(Long id);

    public Alumno create(Alumno alumno);

    public void delete(Long id);
}
