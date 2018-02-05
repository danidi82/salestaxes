package it.dd.salestaxes.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.dd.salestaxes.model.Sales;

@Configuration
public class AppConfig {

	@Bean
	public Sales readJsonWithObjectMapper() throws IOException {
		Sales sales = new Sales();
		Set<String> set = new HashSet<String>();
		set.add("book");
		set.add("chocolate");
		set.add("chocolates");
		set.add("headache pills");
		sales.setExempted(set);
		return sales;
		// return new ObjectMapper().readValue(new
		// ClassPathResource("sales.json").getFile(), Sales.class);

	}
}
