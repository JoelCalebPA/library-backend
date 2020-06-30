package com.domain.library.model.api;

import java.io.Serializable;

import com.domain.library.model.Payment;

public class CheckoutRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Payment payment;
	private String token;

	public CheckoutRequest() {
		super();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
