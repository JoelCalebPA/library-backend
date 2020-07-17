package com.domain.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNT")
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DISCOUNT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int disc;

	private String author;

	private String category;

	public Discount() {
		super();
	}

	public long getId() {
		return id;
	}

	public int getDisc() {
		return disc;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDisc(int disc) {
		this.disc = disc;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
