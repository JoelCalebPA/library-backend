package com.domain.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUSCRIPTION")
public class Suscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SUSCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public Suscription() {
	}

}
