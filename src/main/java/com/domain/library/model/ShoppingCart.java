package com.domain.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Client client;
	private List<CartItem> cartItems;
	private double total;

	public ShoppingCart() {
		super();
	}

	@Id
	@Column(name = "CART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID")
	public Client getClient() {
		return client;
	}

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
