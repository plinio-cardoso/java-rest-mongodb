package ie.dsch.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
public class DschAdvertApplication {
	public static void main(String[] args) {
		SpringApplication.run(DschAdvertApplication.class, args);
	}
}
