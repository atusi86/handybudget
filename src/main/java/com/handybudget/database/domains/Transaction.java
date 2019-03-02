package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction {

	@Id
	private int id;

	@OneToOne
	@JoinColumn(name = "transaction_cat_id")
	private TransactionCategory transactionCategory;
	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date due_date;
	private int ispaid;
	private String description;

	@ManyToOne
	private Account account;
	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public Transaction() {
	}

	public Transaction(TransactionCategory transactionCategory, int amount, Date due_date, int ispaid, String description, Account account) {
		this.transactionCategory = transactionCategory;
		this.amount = amount;
		this.due_date = due_date;
		this.ispaid = ispaid;
		this.description = description;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public TransactionCategory getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(TransactionCategory transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
