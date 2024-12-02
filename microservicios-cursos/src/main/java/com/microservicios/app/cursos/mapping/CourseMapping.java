package com.microservicios.app.cursos.mapping;

import com.microservicios.app.cursos.domain.entities.Course;
import com.microservicios.app.cursos.dto.CourseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapping {

    private final ModelMapper modelMapper;


    public CourseMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Course model(CourseDto courseDto){
        return this.modelMapper.map(courseDto,Course.class);
    }

    public CourseDto modelDto(Course course){
        return this.modelMapper.map(course,CourseDto.class);
    }

    public List<CourseDto> modelList(List<Course> courseList){
        return courseList.stream().map(item->modelMapper.map(item, CourseDto.class))
                .collect(Collectors.toList());
    }
}
