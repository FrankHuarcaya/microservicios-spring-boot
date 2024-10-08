package com.microservicios.app.cursos.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("cursoMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public CursoMapping cursoMapping(ModelMapper modelMapper) {
        return new CursoMapping(modelMapper);
    };
}
