package it.dd.salestaxes.model;

public class Receipt extends PurchaseList {
	

	String salesTaxes;
	
	String total;

	public String getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(String salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	


}
