package com.domain.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOK_TO_CART_ITEM")
public class BookToCartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Book book;
	private CartItem cartItem;

	public BookToCartItem() {
		super();
	}

	@Id
	@Column(name = "BOOK_TO_CART_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CART_ITEM_ID")
	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

}
