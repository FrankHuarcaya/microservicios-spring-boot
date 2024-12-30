package com.microservices.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class MicroservicesPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesPaymentsApplication.class, args);
	}

}
