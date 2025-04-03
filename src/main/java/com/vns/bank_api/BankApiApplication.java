package com.vns.bank_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner runQuery(JdbcTemplate jdbcTemplate) {
		return args -> {
			// Check if the table 'admins' has any records
			String checkSql = "SELECT COUNT(*) FROM admins";
			Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class);

			// If no records exist, insert an admin with id 1
			if (count != null && count == 0) {
				String insertSql = "INSERT INTO admins (id) VALUES (1)";
				jdbcTemplate.update(insertSql);
				System.out.println("Admin with id 1 inserted.");
			} else {
				System.out.println("Admins table already has records.");
			}
		};
	}

	@Bean
	public CommandLineRunner createAdminUserIfNotExists(JdbcTemplate jdbcTemplate) {
		return args -> {
			// Check if a user with username 'admin' exists in the 'usuarios' table
			String checkUserSql = "SELECT COUNT(*) FROM usuarios WHERE username = 'admin'";
			Integer userCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class);

			// If no such user exists, insert a new user with id 0, username 'admin', password 'admin', and admin_id = 1
			if (userCount != null && userCount == 0) {
				String insertUserSql = "INSERT INTO usuarios (id, username, password, admin_id) VALUES (0, 'admin', 'admin', 1)";
				jdbcTemplate.update(insertUserSql);
				System.out.println("User with username 'admin' created with id 0.");
			} else {
				System.out.println("User with username 'admin' already exists.");
			}
		};
	}
}
