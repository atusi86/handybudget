package com.handybudget.database.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class IncomingData {

	@Id
	private int id;

	@ManyToOne
	private User user;

	private String content;

	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_timestamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update_timestamp;

	public IncomingData() {
	}

	public IncomingData(String content, User user) {
		super();
		this.content = content;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "IncomingData [id=" + id + ", user=" + user + ", content=" + content + ", deleted=" + deleted + ", create_timestamp=" + create_timestamp + ", update_timestamp=" + update_timestamp + "]";
	}

}
