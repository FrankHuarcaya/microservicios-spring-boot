package com.microservices.app.users.domain.persistence;

import com.microservices.app.users.domain.entites.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {
}
