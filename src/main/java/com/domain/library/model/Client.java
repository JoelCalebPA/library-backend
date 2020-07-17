package com.domain.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLIENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String address;

	@JsonIgnore
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
	private ShoppingCart cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Subscription> subscriptions;

	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Rating> ratings;

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orderList;

	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<ClientPayment> clientPaymentList;

	public Client() {
	}

	public Client(long id) {
		super();
		this.id = id;
	}

	public Client(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public ShoppingCart getShoppingCart() {
		return cart;
	}

	public void setShoppingCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<ClientPayment> getClientPaymentList() {
		return clientPaymentList;
	}

	public void setClientPaymentList(List<ClientPayment> clientPaymentList) {
		this.clientPaymentList = clientPaymentList;
	}
	
	public ShoppingCart getCart() {
		return cart;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}
