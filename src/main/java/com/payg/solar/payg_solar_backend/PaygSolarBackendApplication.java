package com.payg.solar.payg_solar_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PaygSolarBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaygSolarBackendApplication.class, args);
	}

}
