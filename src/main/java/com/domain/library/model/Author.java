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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String description;
	private List<Book> books;

	public Author() {
		super();
	}

	@Id
	@Column(name = "AUTHOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(unique=true)
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	public List<Book> getBooks() {
		return books;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
