package com.microservices.app.users.mapping;

import com.microservices.app.users.domain.entites.Person;
import com.microservices.app.users.dto.PersonDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapping {

    private final ModelMapper modelMapper;

    public PersonMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Person model(PersonDto personDto){
        return this.modelMapper.map(personDto,Person.class);
    }

    public PersonDto modelDto(Person person){
        return this.modelMapper.map(person,PersonDto.class);
    }

    public List<PersonDto> modelList(List<Person> personList){
        return personList.stream().map(item->modelMapper.map(item,PersonDto.class))
                .collect(Collectors.toList());
    }

}
