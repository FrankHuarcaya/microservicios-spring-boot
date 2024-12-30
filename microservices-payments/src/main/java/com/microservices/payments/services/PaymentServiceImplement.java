package com.microservices.payments.services;

import com.microservices.payments.client.CourseService;
import com.microservices.payments.client.UserService;
import com.microservices.payments.domain.entities.Payment;
import com.microservices.payments.domain.persistence.PaymentRepository;
import com.microservices.payments.domain.services.IPaymentService;
import com.microservices.payments.dto.CourseDto;
import com.microservices.payments.dto.PaymentDto;
import com.microservices.payments.dto.PersonDto;
import com.microservices.payments.mapping.PaymentMapping;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
public class PaymentServiceImplement implements IPaymentService {


    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final CourseService courseService;

    private final PaymentMapping paymentMapping;


    public PaymentServiceImplement(PaymentRepository paymentRepository, UserService userService, CourseService courseService, PaymentMapping paymentMapping) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.courseService = courseService;
        this.paymentMapping = paymentMapping;
    }


    @Override
    public Mono<PaymentDto> createPayment(PaymentDto paymentDto) {
        Mono<PersonDto> studentMono = userService.getPersonById(paymentDto.getStudentId());
        Mono<CourseDto> courseMono = courseService.getCourseById(paymentDto.getCourseId());
        return studentMono.zipWith(courseMono)
                .flatMap(tuple -> {
                    PersonDto student = tuple.getT1();
                    CourseDto course = tuple.getT2();

                    Payment payment = paymentMapping.modelDto(paymentDto);
                    payment.setCreatedAt(LocalDateTime.now());

                    return paymentRepository.save(payment)
                            .map(paymentMapping::model);
                });
    }

    @Override
    public Flux<PaymentDto> listPayments() {
        return paymentRepository.findAll()
                .map(paymentMapping::model); // Convertir entidad a DTO
    }

    @Override
    public Mono<PaymentDto> getPaymentById(String id) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    PaymentDto dto = new PaymentDto();
                    dto.setId(payment.getId());
                    dto.setStudentId(payment.getStudentId());
                    dto.setCourseId(payment.getCourseId());
                    dto.setAmount(payment.getAmount());
                    dto.setCreatedAt(payment.getCreatedAt());
                    return dto;
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El pago no existe")));
    }

    @Override
    public Mono<Void> deletePayment(String id) {
        return paymentRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El pago no existe")))
                .flatMap(payment -> paymentRepository.delete(payment));
    }
}
