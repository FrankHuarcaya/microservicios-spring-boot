package com.microservicios.app.cursos.mapping;

import com.microservicios.app.cursos.domain.entities.Enrollment;
import com.microservicios.app.cursos.dto.EnrollmentDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentMapping {

    private final ModelMapper modelMapper;


    public EnrollmentMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Enrollment model(EnrollmentDto enrollmentDto){

        return this.modelMapper.map(enrollmentDto,Enrollment.class);
    }

    public EnrollmentDto modelDto(Enrollment enrollment){

        return this.modelMapper.map(enrollment,EnrollmentDto.class);
    }

    public List<EnrollmentDto> modelList(List<Enrollment> courseList){
        return courseList.stream().map(item->modelMapper.map(item, EnrollmentDto.class))
                .collect(Collectors.toList());
    }
}
