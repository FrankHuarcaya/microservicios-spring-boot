package com.microservicios.app.cursos.domain.persistence;

import com.microservicios.app.cursos.domain.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
}
