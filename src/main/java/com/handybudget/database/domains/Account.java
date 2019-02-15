package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account {

	@Id
	private int id;
	private String name;
	private String description;
	
	//private int user_id;
	
	@ManyToOne
	private User user;
	
	private int deleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;
	
	
	public Account() {
	}
	
	


	public Account(int id, String name, String description, User user, int deleted, Date create_timestamp,
			Date update_timestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.deleted = deleted;
		this.create_timestamp = create_timestamp;
		this.update_timestamp = update_timestamp;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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




	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", description=" + description + ", user=" + user + ", deleted="
				+ deleted + ", create_timestamp=" + create_timestamp + ", update_timestamp=" + update_timestamp + "]";
	}


	
	

	
	
	
	
}
