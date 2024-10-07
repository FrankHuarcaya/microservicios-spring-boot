package com.microservices.app.users.api;


import com.microservices.app.users.domain.entites.Alumno;
import com.microservices.app.users.domain.services.IAlumnoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/alumnos")
public class AlumnoController {

    private final IAlumnoServices alumnoServices;


    public AlumnoController(IAlumnoServices alumnoServices) {
        this.alumnoServices = alumnoServices;
    }

    @GetMapping
    public ResponseEntity<?>listAlumnos(){
        return ResponseEntity.ok().body(alumnoServices.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdAlumno(@PathVariable Long id){
        Optional<Alumno> alumno=alumnoServices.findById(id);
        if(alumno.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno.get());
    }

    @PostMapping
    public ResponseEntity<?> createAlumno(@RequestBody Alumno alumno){
        Alumno alumno1 =alumnoServices.create(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno1);
    }

    @PutMapping
    public ResponseEntity<?> updateAlumno(@RequestBody Alumno alumno,@PathVariable Long id){
        Optional<Alumno> response=alumnoServices.findById(id);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Alumno alumnodb=response.get();
        alumnodb.setNombre(alumno.getNombre());
        alumnodb.setApellido(alumno.getApellido());
        alumnodb.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoServices.create(alumnodb));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id){
        alumnoServices.delete(id);
        return ResponseEntity.noContent().build();
    }



}
