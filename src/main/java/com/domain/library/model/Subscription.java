package com.domain.library.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SUBSCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String startingDate;

	private String endingDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	public Subscription() {
	}

	public long getId() {
		return id;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public String getStatus() {
		return status;
	}

	public Client getClient() {
		return client;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
