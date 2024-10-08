package com.microservicios.app.cursos.mapping;

import com.microservicios.app.cursos.domain.entities.Curso;
import com.microservicios.app.cursos.dto.CursoDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CursoMapping {

    private final ModelMapper modelMapper;

    public CursoMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    public Curso model(CursoDto cursoDto){
        return this.modelMapper.map(cursoDto,Curso.class);
    }

    public CursoDto modelDto(Curso curso){
        return this.modelMapper.map(curso,CursoDto.class);
    }

    public List<CursoDto> modelList(List<Curso> modelList){
        return modelList.stream().map(item->modelMapper.map(item,CursoDto.class))
                .collect(Collectors.toList());
    }

}
