package it.dd.salestaxes.model;

import java.util.Set;

public class Sales extends BaseBean {

	
	Set<String> exempted;

	public Set<String> getExempted() {
		return exempted;
	}

	public void setExempted(Set<String> exempted) {
		this.exempted = exempted;
	}
	
}
