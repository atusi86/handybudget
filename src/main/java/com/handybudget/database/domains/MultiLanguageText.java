package com.handybudget.database.domains;

import java.util.Date;

public class MultiLanguageText {

	private int id;
	private String hu;
	private String en;
	private String de;
	private int deleted;
	private Date create_timestamp;
	private Date update_timestamp;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHu() {
		return hu;
	}
	public void setHu(String hu) {
		this.hu = hu;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
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
