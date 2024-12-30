package com.microservices.payments.api;

import com.microservices.payments.domain.services.IPaymentService;
import com.microservices.payments.dto.PaymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentDto>> createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto)
                .map(savedPayment -> ResponseEntity.status(HttpStatus.CREATED).body(savedPayment));
    }

    @GetMapping
    public Flux<PaymentDto> listPayments() {
        return paymentService.listPayments();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PaymentDto>> getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePayment(@PathVariable String id) {
        return paymentService.deletePayment(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
