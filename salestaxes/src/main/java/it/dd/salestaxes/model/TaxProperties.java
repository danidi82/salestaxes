package it.dd.salestaxes.model;

import java.util.Set;

public class TaxProperties extends BaseBean {

	
	private Set<String> exemptedPatterns;

	private String importedPattern;
	
	private int basicTaxRate;
	
	private int importedTaxRate;
	
	private float roudingUp;

	public Set<String> getExemptedPatterns() {
		return exemptedPatterns;
	}

	public void setExemptedPatterns(Set<String> exemptedPatterns) {
		this.exemptedPatterns = exemptedPatterns;
	}

	public String getImportedPattern() {
		return importedPattern;
	}

	public void setImportedPattern(String importedPattern) {
		this.importedPattern = importedPattern;
	}

	public int getBasicTaxRate() {
		return basicTaxRate;
	}

	public void setBasicTaxRate(int basicTaxRate) {
		this.basicTaxRate = basicTaxRate;
	}

	public int getImportedTaxRate() {
		return importedTaxRate;
	}

	public void setImportedTaxRate(int importedTaxRate) {
		this.importedTaxRate = importedTaxRate;
	}

	public float getRoudingUp() {
		return roudingUp;
	}

	public void setRoudingUp(float roudingUp) {
		this.roudingUp = roudingUp;
	}
	
	

	
}
