package com.microservices.payments.mapping;

import com.microservices.payments.domain.entities.Payment;
import com.microservices.payments.dto.PaymentDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapping {

    private final ModelMapper modelMapper;

    public PaymentMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Payment modelDto(PaymentDto paymentDto) {
        return this.modelMapper.map(paymentDto, Payment.class);
    }

    public PaymentDto model(Payment payment) {
        return this.modelMapper.map(payment, PaymentDto.class);
    }

    public List<PaymentDto> modelList(List<Payment> examList) {
        return examList.stream()
                .map(item -> modelMapper.map(item, PaymentDto.class))
                .collect(Collectors.toList());
    }
}
