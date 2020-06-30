package com.domain.library.model;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "CART_ITEM")
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private ShoppingCart shoppingCart;
	private Book book;
	private int quantity;
	private double subtotal;
	private List<BookToCartItem> bookToCartItemList;
	private Order order;

	@Id
	@Column(name = "CART_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CART_ID")
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	@OneToOne
	@JoinColumn(name = "BOOK_ID")
	public Book getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "cartItem")
	public List<BookToCartItem> getBookToCartItemList() {
		return bookToCartItemList;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	public Order getOrder() {
		return order;
	}

	public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
		this.bookToCartItemList = bookToCartItemList;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setShoppingCart(ShoppingCart cart) {
		this.shoppingCart = cart;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
