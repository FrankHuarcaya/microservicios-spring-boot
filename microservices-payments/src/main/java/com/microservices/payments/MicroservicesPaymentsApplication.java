package com.microservices.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class MicroservicesPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesPaymentsApplication.class, args);
	}

}
