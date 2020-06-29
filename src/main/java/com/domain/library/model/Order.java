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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENT_ORDER")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String orderDate;
	private double orderTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PAYMENT_ID")
	private Payment payment;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<CartItem> cartItemList;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	public long getId() {
		return id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public Payment getPayment() {
		return payment;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
