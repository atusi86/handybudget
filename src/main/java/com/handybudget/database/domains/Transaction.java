package com.handybudget.database.domains;

import java.util.Date;

public class Transaction {

	private int id;
	private int transaction_cat_id;
	private int transaction_type_id;
	private int amount;
	private Date due_date;
	private int ispaid;
	private String description;
	private int account_id;
	private int deleted;
	private Date create_timestamp;
	private Date update_timestamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTransaction_cat_id() {
		return transaction_cat_id;
	}
	public void setTransaction_cat_id(int transaction_cat_id) {
		this.transaction_cat_id = transaction_cat_id;
	}
	public int getTransaction_type_id() {
		return transaction_type_id;
	}
	public void setTransaction_type_id(int transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public int getIspaid() {
		return ispaid;
	}
	public void setIspaid(int ispaid) {
		this.ispaid = ispaid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
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
