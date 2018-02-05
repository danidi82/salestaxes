package it.dd.salestaxes.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.dd.salestaxes.model.TaxProperties;

@Configuration
public class AppConfig {

	@Bean
	public TaxProperties readJsonWithObjectMapper() throws IOException {
		TaxProperties props = new TaxProperties();
		Set<String> set = new HashSet<String>();
		set.add("book");
		set.add("chocolate");
		set.add("chocolates");
		set.add("headache pills");
		props.setExemptedPatterns(set);
		props.setBasicTaxRate(20);
		props.setImportedTaxRate(5);
		return props;
		// return new ObjectMapper().readValue(new
		// ClassPathResource("sales.json").getFile(), Sales.class);

	}
}
