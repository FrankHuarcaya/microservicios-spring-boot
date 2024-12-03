package com.microservicios.app.cursos.domain.persistence;

import com.microservicios.app.cursos.domain.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
}
