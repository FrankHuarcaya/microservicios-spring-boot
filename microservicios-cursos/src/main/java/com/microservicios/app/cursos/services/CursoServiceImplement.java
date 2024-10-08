package com.microservicios.app.cursos.services;

import com.microservicios.app.cursos.domain.entities.Curso;
import com.microservicios.app.cursos.domain.persistence.CursoRepository;
import com.microservicios.app.cursos.domain.services.ICursoService;
import com.microservicios.app.cursos.dto.CursoDto;
import com.microservicios.app.cursos.mapping.CursoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImplement implements ICursoService {


    private final CursoRepository cursoRepository;

    private final CursoMapping mapping;

    public CursoServiceImplement(CursoRepository cursoRepository, CursoMapping mapping) {
        this.cursoRepository = cursoRepository;
        this.mapping = mapping;
    }


    @Override
    public List<CursoDto> listCursos() {

        return mapping.modelList(cursoRepository.findAll());
    }

    @Override
    public CursoDto registerCurso(CursoDto cursoDto) {
        Curso curso= mapping.model(cursoDto);
        return  mapping.modelDto(cursoRepository.save(curso));
    }

    @Override
    public CursoDto updateCurso(Long id,CursoDto cursoDto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre(cursoDto.getNombre());
        Curso updatedCurso = cursoRepository.save(curso);
        return mapping.modelDto(updatedCurso);
    }


    @Override
    public void deleteCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        cursoRepository.delete(curso);
    }
}
