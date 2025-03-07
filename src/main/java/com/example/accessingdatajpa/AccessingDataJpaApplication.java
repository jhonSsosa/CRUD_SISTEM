package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Property;
import com.example.accessingdatajpa.repository.PropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PropertyRepository repository) {
		return (args) -> {
			repository.save(new Property("123 Main St", 200000, 120.5, "Beautiful house"));
			repository.save(new Property("456 Elm St", 150000, 100.0, "Cozy apartment"));
			repository.save(new Property("789 Oak St", 300000, 200.0, "Spacious villa"));
		};
	}
}
