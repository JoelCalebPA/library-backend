package com.domain.library.model.api;

public class UpdateUserRequest {

	private long id;
	private String email;
	private String address;

	public UpdateUserRequest() {
		super();
	}

	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
