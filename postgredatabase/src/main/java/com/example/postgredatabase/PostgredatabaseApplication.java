package com.example.postgredatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.concurrent.Executor;

@SpringBootApplication
public class PostgredatabaseApplication implements CommandLineRunner {

	private JdbcTemplate jdbcTemplate;

	public PostgredatabaseApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(PostgredatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("SELECT 1");
	}
}
