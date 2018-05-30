package ie.dsch.services.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories("ie.dsch")
@ComponentScan("ie.dsch")
public class DschAdvertApplication {
	public static void main(String[] args) {
		SpringApplication.run(DschAdvertApplication.class, args);
	}
}
