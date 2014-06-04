package br.com.fluentcode.mockito.entity;

public class Billet {
	
	double amountPayable;
	
	public Billet() {
	}
	
	public Billet(double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public double getAmountPayable() {
		return amountPayable;
	}
	
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}

}
