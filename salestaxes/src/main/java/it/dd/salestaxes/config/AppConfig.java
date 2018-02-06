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
		Set<String> exempted = new HashSet<String>();
		exempted.add("book");
		exempted.add("chocolate");
		exempted.add("chocolates");
		exempted.add("headache pills");
		props.setExemptedPatterns(exempted);
		
		Set<String> imported = new HashSet<String>();
		imported.add("imported");
		props.setImportedPatterns(imported);
		
		props.setBasicTaxRate(10);
		props.setImportedTaxRate(5);
		return props;
		// return new ObjectMapper().readValue(new
		// ClassPathResource("sales.json").getFile(), Sales.class);

	}
}
