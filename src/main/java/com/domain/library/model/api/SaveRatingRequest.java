package com.domain.library.model.api;

import java.io.Serializable;

public class SaveRatingRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private long book;
	private long client;
	private String clientName;
	private String comment;
	private String date;

	public SaveRatingRequest() {
		super();
	}

	public long getBook() {
		return book;
	}

	public long getClient() {
		return client;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}

	public void setBook(long book) {
		this.book = book;
	}

	public void setClient(long client) {
		this.client = client;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}
