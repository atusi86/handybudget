package com.handybudget.database.domains;

import java.util.Date;

public class TransactionCategory {

	private int id;
	private int user_id;
	private String name;
	private String description;
	private int deleted;
	private Date create_timestamp;
	private Date update_timestamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
	
	
	
}
