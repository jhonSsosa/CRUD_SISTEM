package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Property;
import com.example.accessingdatajpa.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.datasource.driverClassName=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
public class PropertyRepositoryTests {

	@Autowired
	private PropertyRepository propertyRepository;

	@Test
	public void testSaveAndFindByAddress() {
		Property property = new Property("123 Main St", 100000.0, 120.5, "Nice house");
		propertyRepository.save(property);
		List<Property> results = propertyRepository.findAll();
		assertThat(results)
				.isNotEmpty()
				.extracting(Property::getAddress)
				.contains("123 Main St");
	}
}
