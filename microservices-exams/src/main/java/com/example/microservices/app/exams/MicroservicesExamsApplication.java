package com.example.microservices.app.exams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@EnableFeignClients
public class MicroservicesExamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesExamsApplication.class, args);
	}

}
