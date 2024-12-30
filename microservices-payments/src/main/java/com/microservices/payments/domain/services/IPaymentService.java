package com.microservices.payments.domain.services;

import com.microservices.payments.dto.PaymentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPaymentService {

    Mono<PaymentDto> createPayment(PaymentDto paymentDto);
    Flux<PaymentDto> listPayments();
    Mono<PaymentDto> getPaymentById(String id);
    Mono<Void> deletePayment(String id);
}
