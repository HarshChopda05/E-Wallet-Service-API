package com.example.ewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EWalletServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EWalletServiceApiApplication.class, args);
	}

}
