package com.microservices.payments.domain.persistence;

import com.microservices.payments.domain.entities.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentRepository extends ReactiveMongoRepository<Payment,String> {
}
