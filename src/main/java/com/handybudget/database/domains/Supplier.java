package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Supplier {

	@Id
	private int id;

	private String name;
	private String phone;
	private String description;
	private int customer_id;

	@ManyToOne
	private User user;

	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public Supplier() {
	}

	public Supplier(String name, String phone, String description, int customer_id, User user) {
		super();
		this.name = name;
		this.phone = phone;
		this.description = description;
		this.customer_id = customer_id;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + ", description=" + description + ", customer_id=" + customer_id + ", user=" + user + ", deleted=" + deleted + ", create_timestamp=" + create_timestamp + ", update_timestamp=" + update_timestamp + "]";
	}

}
