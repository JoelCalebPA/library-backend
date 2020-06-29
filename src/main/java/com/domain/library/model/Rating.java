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

@Entity
@Table(name = "RATING")
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Book book;
	private Client client;
	private String comment;
	private String date;

	public Rating() {
		super();
	}

	@Id
	@Column(name = "RATING_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	public Book getBook() {
		return book;
	}

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	public Client getClient() {
		return client;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
