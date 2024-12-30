package com.microservices.payments.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("examMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public  PaymentMapping paymentMapping(ModelMapper modelMapper){
        return new PaymentMapping (modelMapper);
    }

}
