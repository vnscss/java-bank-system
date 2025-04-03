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
	public CommandLineRunner createAdminUserIfNotExists(JdbcTemplate jdbcTemplate) {
		return args -> {
			// Check if a user with username 'admin' exists in the 'usuarios' table
			String checkUserSql = "SELECT COUNT(*) FROM usuarios WHERE username = 'admin'";
			Integer userCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class);

			// If no such user exists, insert a new user with id 0, username 'admin', password 'admin'
			if (userCount != null && userCount == 0) {
				String insertUserSql = "INSERT INTO usuarios (id, username, password) VALUES (0, 'admin', 'admin')";
				jdbcTemplate.update(insertUserSql);
				System.out.println("User with username 'admin' created with id 0.");
			} else {
				System.out.println("User with username 'admin' already exists.");
			}

			// Check if a 'gerente' with id 0 exists in the 'gerentes' table
			String checkGerenteSql = "SELECT COUNT(*) FROM gerentes WHERE id = 0";
			Integer gerenteCount = jdbcTemplate.queryForObject(checkGerenteSql, Integer.class);

			// If no such 'gerente' exists, insert a new 'gerente' with the given values
			if (gerenteCount != null && gerenteCount == 0) {
				String insertGerenteSql = "INSERT INTO gerentes (id, cpf, nome, data_nasc, role, user_id) VALUES (0, '0', 'gerenteAdmin', 0, '[\"ROLE_GERENTE\"]', 0)";
				jdbcTemplate.update(insertGerenteSql);
				System.out.println("Gerente with id 0 created.");
			} else {
				System.out.println("Gerente with id 0 already exists.");
			}
		};
	}

}
