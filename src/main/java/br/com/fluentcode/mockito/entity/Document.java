package br.com.fluentcode.mockito.entity;

public class Document {
	
	private boolean expired;
	
	private String status;
	
	private double premium;
	
	public Document() {
	}

	public Document(String status) {
		this.status = status;
	}
	
	public Document(double premium) {
		this.premium = premium;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getPremium() {
		return premium;
	}
	
	public void setPremium(double premium) {
		this.premium = premium;
	}
	
}
