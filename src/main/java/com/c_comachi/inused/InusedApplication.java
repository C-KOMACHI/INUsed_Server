package com.c_comachi.inused;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InusedApplication {

	public static void main(String[] args) {
		SpringApplication.run(InusedApplication.class, args);
	}

}
