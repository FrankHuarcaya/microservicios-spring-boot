package com.microservices.app.users.domain.persistence;

import com.microservices.app.users.domain.entites.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
