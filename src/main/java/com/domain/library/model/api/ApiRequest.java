package com.domain.library.model.api;

import java.io.Serializable;

public class ApiRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;

	public ApiRequest() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
