package it.dd.salestaxes.model;

import java.util.Set;

public class TaxProperties extends BaseBean {

	
	private Set<String> exemptedPatterns;

	private Set<String> importedPatterns;
	
	private int basicTaxRate;
	
	private int importedTaxRate;
	
	private float roudingUp;

	public Set<String> getExemptedPatterns() {
		return exemptedPatterns;
	}

	public void setExemptedPatterns(Set<String> exemptedPatterns) {
		this.exemptedPatterns = exemptedPatterns;
	}

	public Set<String> getImportedPatterns() {
		return importedPatterns;
	}

	public void setImportedPatterns(Set<String> importedPatterns) {
		this.importedPatterns = importedPatterns;
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
