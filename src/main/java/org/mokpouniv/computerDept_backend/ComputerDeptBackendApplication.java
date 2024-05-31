package org.mokpouniv.computerDept_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableMongoRepositories
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ComputerDeptBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComputerDeptBackendApplication.class, args);
	}

}
