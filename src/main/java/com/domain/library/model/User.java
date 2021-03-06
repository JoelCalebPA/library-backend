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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	@JsonIgnore
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private Boolean active;
	private Client client;
	private List<Role> roles;

	public User() {
		super();
	}

	public User(String email, String password) {
		this.password = password;
		this.email = email;
	}

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Column(unique=true)
	public String getEmail() {
		return email;
	}

	public Boolean getActive() {
		return active;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
	public Client getClient() {
		return client;
	}

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(referencedColumnName = "ROLE_ID"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void susbcribe(Role role) {
		roles.add(role);
	}

	public void unsubscribe(Role role) {
		roles.remove(role);
	}

}
