package com.microservices.app.users.services;

import com.microservices.app.users.domain.entites.Alumno;
import com.microservices.app.users.domain.persistence.AlumnoRepository;
import com.microservices.app.users.domain.services.IAlumnoServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AlumnoServicesImplement implements IAlumnoServices {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServicesImplement(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> list() {
        return alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional
    public Alumno create(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }
}
