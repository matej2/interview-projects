package com.example.postgredatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostgredatabaseApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PostgredatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) {
	}
}
