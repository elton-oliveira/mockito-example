package br.com.fluentcode.mockito.entity;

public class Document {
	
	private boolean expired;
	
	private String status;
	
	public Document() {
	}

	public Document(String status) {
		this.status = status;
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
	
}
