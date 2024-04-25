package org.mokpouniv.computerDept_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class ComputerDeptBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputerDeptBackendApplication.class, args);
	}

}
