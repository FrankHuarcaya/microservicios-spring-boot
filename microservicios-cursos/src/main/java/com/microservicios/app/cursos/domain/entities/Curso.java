package com.microservicios.app.cursos.domain.entities;

import com.microservices.app.users.domain.entites.Alumno;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    public Curso(){
        this.alumnos=new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt= new Date();
    }
    
    public void addAlumnos(Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void removeAlumnos(Alumno alumno){
        this.alumnos.remove(alumno);
    }
}
