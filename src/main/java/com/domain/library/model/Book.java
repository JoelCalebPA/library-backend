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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String title;
	private String description;
	private Author author;
	private Category category;
	private Publisher publisher;
	private String publicationDate;
	private String isbn;
	private int stock;
	private double price;
	private List<Rating> ratings;
	private boolean active = true;

	public Book() {
		super();
	}

	public Book(long id) {
		super();
		this.id = id;
	}

	@Id
	@Column(name = "BOOK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(unique = true)
	public String getTitle() {
		return title;
	}

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	public Author getAuthor() {
		return author;
	}

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	@ManyToOne
	@JoinColumn(name = "PUBLISHER_ID")
	public Publisher getPublisher() {
		return publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public boolean isActive() {
		return active;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

}
