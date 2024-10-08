package com.microservicios.app.cursos.domain.services;

import com.microservicios.app.cursos.dto.CursoDto;

import java.util.List;

public interface ICursoService {

    List<CursoDto> listCursos();

    CursoDto registerCurso(CursoDto cursoDto);

    CursoDto updateCurso (Long id,CursoDto cursoDto);


    void deleteCurso(Long id);
}
