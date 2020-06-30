package com.domain.library.model.api;

import java.io.Serializable;

public class AddCartItemRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private long bookId;
	private long cartItemId;
	private int quantity;
	private String token;

	public AddCartItemRequest() {
		super();
	}

	public long getBookId() {
		return bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
