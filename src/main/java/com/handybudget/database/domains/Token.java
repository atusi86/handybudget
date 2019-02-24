package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Token {

	@Id
	private int id;
	private String identifier;

	@Column(name = "expires_in")
	private int expiresIn;
	private int deleted;

	@OneToOne(cascade = { CascadeType.ALL })
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public Token(String identifier, int expiresIn, int deleted, Date create_timestamp, Date update_timestamp) {
		this.identifier = identifier;
		this.expiresIn = expiresIn;
	}

	public Token() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getCreate_timestamp() {
		return create_timestamp;
	}

	public void setCreate_timestamp(Date create_timestamp) {
		this.create_timestamp = create_timestamp;
	}

	public Date getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Date update_timestamp) {
		this.update_timestamp = update_timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
